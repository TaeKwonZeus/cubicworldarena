package net.cubicworld;

import net.cubicworld.events.BlockListener;
import net.cubicworld.events.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class Plugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BlockListener(), this);
        pluginManager.registerEvents(new PlayerListener(), this);
    }
}
