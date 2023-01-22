package net.cubicworld;

import lombok.Getter;
import net.cubicworld.events.BlockListener;
import net.cubicworld.events.PlayerListener;
import net.cubicworld.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class Plugin extends JavaPlugin implements Listener {
    @Getter
    private Properties config;

    @Getter
    private Database database;

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new BlockListener(), this);
        pluginManager.registerEvents(new PlayerListener(), this);

        try {
            config = Config.getConfig("config.properties");
            database = new Database(config);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Unable to get configuration file");
            Bukkit.shutdown();
            return;
        }

        if (!database.testConnection()) {
            Bukkit.getLogger().log(Level.SEVERE, "Unable to connect to database");
            Bukkit.shutdown();
        }
    }
}
