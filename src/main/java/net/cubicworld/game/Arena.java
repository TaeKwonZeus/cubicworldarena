package net.cubicworld.game;

import net.cubicworld.game.heroes.Hero;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface Arena {
    Map<Player, @NotNull Hero> getRedTeam();

    void start();

    void stop();
}
