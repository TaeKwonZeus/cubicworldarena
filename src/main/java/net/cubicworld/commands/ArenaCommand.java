package net.cubicworld.commands;

import de.tr7zw.nbtapi.NBT;
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

    private @NotNull Inventory getInventory(InventoryHolder inventoryHolder) {
        ItemStack itemStack = new ItemStack(Material.GREEN_WOOL);
        itemStack.getItemMeta().displayName(Component.text("Create arena"));
        NBT.modify(itemStack, nbt -> {
            nbt.setString("onClick", "createArena");
        });

        Inventory inventory = Bukkit.createInventory(inventoryHolder, InventoryType.CHEST);
        inventory.setItem(4, itemStack);

        return inventory;
    }
}
