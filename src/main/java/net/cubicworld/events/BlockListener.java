package net.cubicworld.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public final class BlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(@NotNull final BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(@NotNull final BlockPlaceEvent event) {
        event.setCancelled(true);
    }
}
