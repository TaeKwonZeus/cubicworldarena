package net.cubicworld.events;

import de.tr7zw.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        ItemStack itemStack = event.getCurrentItem();

        if (Objects.equals(NBT.get(itemStack, nbt -> nbt.getString("onClick")), "createArena")) {
            event.getWhoClicked()
                    .sendMessage(Component.text("You clicked on the green wool!").color(NamedTextColor.GREEN));
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(@NotNull InventoryDragEvent event) {
        event.setCancelled(true);
    }
}
