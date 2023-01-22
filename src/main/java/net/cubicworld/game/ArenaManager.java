package net.cubicworld.game;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArenaManager {
    private final List<Arena> arenas = new ArrayList<>();

    public void register(@NotNull Arena arena) {
        arenas.add(arena);
    }

    public void unregister(@NotNull Arena arena) {
        arenas.remove(arena);
    }

    public @NotNull @UnmodifiableView List<Arena> list() {
        return Collections.unmodifiableList(arenas);
    }

    public void stopServer() {
        for (Arena arena : arenas) arena.stop();

        for (Player player : Bukkit.getOnlinePlayers())
            player.kick(Component.text("Server is shutting down", NamedTextColor.RED));
        Bukkit.shutdown();
    }
}
