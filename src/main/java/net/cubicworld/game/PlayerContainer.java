package net.cubicworld.game;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

public interface PlayerContainer {
    @NotNull @UnmodifiableView List<@NotNull Player> getPlayers();

    void addPlayer(@NotNull Player player);

    void removePlayer(@NotNull Player player);

    boolean containsPlayer(@NotNull Player player);
}
