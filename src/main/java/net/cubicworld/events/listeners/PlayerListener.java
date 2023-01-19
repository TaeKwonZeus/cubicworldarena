package net.cubicworld.events.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public final class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
    }
}
