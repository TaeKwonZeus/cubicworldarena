package net.cubicworld.game;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.IOException;
import java.util.*;

public class Game implements PlayerContainer {
    private final World world;
    private final List<Team> teams;

    public Game(String name, int n) throws IOException {
        world = new WorldCreator("lobbies/" + name).environment(World.Environment.NORMAL)
                .type(WorldType.NORMAL)
                .createWorld();

        teams = Team.getTeams(List.of(
                new Location(world, 0, 0, 0),
                new Location(world, 20, 20, 20)
        ));
    }

    @Override
    public @NotNull @UnmodifiableView List<@NotNull Player> getPlayers() {
        return teams.stream().map(Team::getPlayers).flatMap(List::stream).toList();
    }

    @Override
    public void addPlayer(@NotNull Player player) {
        Team smallestTeam = teams.stream()
                .reduce((team, team2) -> team.size() > team2.size() ? team : team2)
                .orElseThrow();

        smallestTeam.addPlayer(player);
    }

    @Override
    public void removePlayer(@NotNull Player player) {
        if (!getPlayers().contains(player)) return;
        for (Team team : teams) {
            team.removePlayer(player);
        }

        player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
        world.sendMessage(Component.text(player.getName() + " has quit!"));
    }

    @Override
    public boolean containsPlayer(@NotNull Player player) {
        return teams.stream().anyMatch(team -> team.containsPlayer(player));
    }
}
