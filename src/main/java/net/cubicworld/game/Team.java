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
    public @NotNull @UnmodifiableView List<Player> getPlayers() {
        return ids.stream().map(Bukkit::getPlayer).toList();
    }

    @Override
    public void addPlayer(@NotNull Player player) {
        if (!containsPlayer(player)) ids.add(player.getUniqueId());
    }

    @Override
    public void removePlayer(@NotNull Player player) {
        ids.remove(player.getUniqueId());
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
