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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class Team implements PlayerContainer {
    private final List<UUID> ids = new ArrayList<>();
    @Getter
    private final TextColor color;
    @Getter
    private final Location spawn;

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

    @Getter
    private static final List<TextColor> colors = List.of(NamedTextColor.RED, NamedTextColor.BLUE);

    public static @NotNull List<Team> getTeams(@NotNull List<Location> spawns) throws IOException {
        if (spawns.size() > colors.size())
            throw new IOException("Unable to create " + spawns.size() + "teams");

        return IntStream.range(0, spawns.size()).mapToObj(i -> new Team(colors.get(i), spawns.get(i))).toList();
    }
}
