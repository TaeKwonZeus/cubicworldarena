package net.cubicworld.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class Team implements PlayerContainer {
    private static final List<TextColor> colors = List.of(NamedTextColor.RED, NamedTextColor.BLUE);
    private final List<UUID> ids = new ArrayList<>();
    @Getter
    private final TextColor color;
    @Getter
    private final Location spawn;

    public static @NotNull List<Team> getTeams(@NotNull List<Location> spawns) {
        List<TextColor> colorList = Collections.nCopies((spawns.size() + colors.size() - 1) / colors.size(), colors)
                .stream()
                .flatMap(List::stream)
                .limit(spawns.size())
                .toList();

        return IntStream.range(0, spawns.size()).mapToObj(i -> new Team(colorList.get(i), spawns.get(i))).toList();
    }

    public int size() {
        return ids.size();
    }

    @Override
    public @NotNull @UnmodifiableView List<@NotNull Player> getPlayers() {
        return ids.stream().map(Bukkit::getPlayer).map(Objects::requireNonNull).toList();
    }

    @Override
    public void addPlayer(@NotNull Player player) {
        if (containsPlayer(player)) return;

        ids.add(player.getUniqueId());
        player.teleport(spawn);
    }

    @Override
    public void removePlayer(@NotNull Player player) {
        if (!containsPlayer(player)) return;

        ids.remove(player.getUniqueId());
        player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
    }

    @Override
    public boolean containsPlayer(@NotNull Player player) {
        return ids.contains(player.getUniqueId());
    }
}
