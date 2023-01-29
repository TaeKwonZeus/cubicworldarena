package net.cubicworld.matchmaking;

import lombok.AllArgsConstructor;
import net.cubicworld.game.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
public class Lobby<T extends Arena> {
    private final LobbyManager<T> lobbyManager;

    private final List<UUID> redPlayers = new ArrayList<>();

    private final List<UUID> bluePlayers = new ArrayList<>();

    public void start() {

    }

    public void stop() {
        lobbyManager.removeLobby(this);
    }

    public void registerPlayer(@NotNull Player player) {
        UUID uuid = player.getUniqueId();

        if (redPlayers.size() > bluePlayers.size()) bluePlayers.add(uuid);
        else if (bluePlayers.size() > redPlayers.size()) redPlayers.add(uuid);
        else {
            boolean condition = new Random(Bukkit.getCurrentTick()).nextBoolean();

            if (condition) redPlayers.add(uuid);
            else bluePlayers.add(uuid);
        }
    }

    public void unregisterPlayer(@NotNull Player player) {
        UUID uuid = player.getUniqueId();

        redPlayers.remove(uuid);
        bluePlayers.remove(uuid);

        if (redPlayers.isEmpty() && bluePlayers.isEmpty()) {
            stop();
        }
    }
}
