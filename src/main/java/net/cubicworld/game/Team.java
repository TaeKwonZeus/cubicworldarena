package net.cubicworld.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class Team {
    private final List<Player> players = new ArrayList<>();
    @Getter
    private final TextColor color;

    @Getter
    private final Location spawn;

    public @NotNull @UnmodifiableView List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int size() {
        return players.size();
    }

    public void addPlayer(@NotNull Player player) {
        if (players.contains(player)) return;
        players.add(player);
    }

    public void removePlayer(@NotNull Player player) {
        players.remove(player);
    }

    public boolean containsPlayer(@NotNull Player player) {
        return players.contains(player);
    }

    @Getter
    private static final List<TextColor> colors = List.of(NamedTextColor.RED, NamedTextColor.BLUE);

    public static @NotNull List<Team> getTeams(@NotNull List<Location> spawns) throws IOException {
        if (spawns.size() > colors.size())
            throw new IOException("Unable to create " + spawns.size() + "teams");

        return IntStream.range(0, spawns.size()).mapToObj(i -> new Team(colors.get(i), spawns.get(i))).toList();
    }
}
