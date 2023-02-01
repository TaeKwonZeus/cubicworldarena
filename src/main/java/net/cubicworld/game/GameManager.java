package net.cubicworld.game;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private final Map<String, Game> games = new HashMap<>();

    public @Nullable Game addGame(@NotNull String name, int teams) throws IOException {
        String normalizedName = name.strip().toLowerCase();

        Game game = new Game(normalizedName, teams);
        if (games.containsKey(normalizedName)) return null;

        games.put(normalizedName, game);
        return game;
    }

    public @Nullable Game addGame(@NotNull String name) throws IOException {
        return addGame(name, 2);
    }

    public void addGamePlayer(@NotNull Player player, @NotNull Game game) {
        game.addPlayer(player);
    }

    public void leaveLobby(@NotNull Player player) {
        for (Game game : games.values()) game.removePlayer(player);
    }

    public void removeLobby(@NotNull Game game) {
        games.values().remove(game);
    }
}
