package net.cubicworld.commands;

import net.cubicworld.util.MetadataUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Sender isn't an instance of Player"));
            return false;
        }
        player.openInventory(getInventory(player));

        return true;
    }

    private Inventory getInventory(InventoryHolder inventoryHolder) {
        ItemStack createArenaItemStack = new ItemStack(Material.GREEN_WOOL);
        createArenaItemStack.getItemMeta().displayName(Component.text("Create arena"));
        MetadataUtil.setTags(createArenaItemStack, "createArena");

        Inventory inventory = Bukkit.createInventory(inventoryHolder, InventoryType.CHEST);
        inventory.setItem(4, createArenaItemStack);

        return inventory;
    }
}
