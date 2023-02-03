package net.cubicworld.game;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PlayerContainer {
    @NotNull List<@NotNull Player> getPlayers();

    void addPlayer(@NotNull Player player);

    void removePlayer(@NotNull Player player);

    boolean containsPlayer(@NotNull Player player);
}
