package net.cubicworld.events;

import lombok.AllArgsConstructor;
import net.cubicworld.util.MetadataUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Level;

@AllArgsConstructor
public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        ItemStack itemStack = event.getCurrentItem();
        List<String> tags = MetadataUtil.getTags(itemStack);

        if (tags == null) {
            Bukkit.getLogger().log(Level.INFO, "Item has no lore!");
            return;
        }

        Bukkit.getLogger().log(Level.INFO, tags.toString());

        if (tags.contains("createArena")) {
            event.getWhoClicked()
                    .sendMessage(Component.text("You clicked on the green wool!").color(NamedTextColor.GREEN));
        }
    }
}
