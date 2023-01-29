package net.cubicworld.matchmaking;

import net.cubicworld.game.Arena;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class LobbyManager<T extends Arena> {
    private final Map<String, Lobby<T>> lobbies = new HashMap<>();

    public void createAndJoinLobby(@NotNull String name, @NotNull Player player) {
        Lobby<T> lobby = new Lobby<>(this);
        lobbies.put(name, lobby);
        lobby.registerPlayer(player);
    }

    public void joinLobby(@NotNull String name, @NotNull Player player) {
        Lobby<T> lobby = lobbies.get(name);
        if (lobby == null) {
            player.sendMessage(Component.text("Lobby with this name doesn't exist", NamedTextColor.RED));
            return;
        }

        lobby.registerPlayer(player);
    }

    public void removeLobby(@NotNull Lobby<T> lobby) {
        lobbies.values().remove(lobby);
    }
}
