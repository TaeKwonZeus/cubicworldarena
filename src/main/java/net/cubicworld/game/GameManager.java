package net.cubicworld.game;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private final Map<String, Game> games = new HashMap<>();

    public @Nullable Game addGame(@NotNull String name) {
        String normalizedName = name.strip().toLowerCase();

        Game game = new Game(normalizedName);
        if (games.containsKey(normalizedName)) return null;

        games.put(normalizedName, game);
        return game;
    }

    public @NotNull @UnmodifiableView Map<String, Game> getGames() {
        return Collections.unmodifiableMap(games);
    }

    public @Nullable Game getGame(@NotNull String name) {
        return games.get(name);
    }

    public @Nullable Game getGame(@NotNull Player player) {
        return games.values().stream().filter(game -> game.containsPlayer(player)).findFirst().orElse(null);
    }

    public void removeGame(@NotNull Game game) {
        games.values().remove(game);
    }
}
