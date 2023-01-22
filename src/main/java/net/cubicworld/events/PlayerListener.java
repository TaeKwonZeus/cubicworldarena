package net.cubicworld.events;

import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final @NotNull PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
    }

    @EventHandler
    public void onPlayerInteract(final @NotNull PlayerInteractEvent event) {
        // TODO Implement player interaction
    }
}
