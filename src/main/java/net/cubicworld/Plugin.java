package net.cubicworld;

import lombok.Getter;
import net.cubicworld.commands.ArenaCommand;
import net.cubicworld.events.BlockListener;
import net.cubicworld.events.PlayerListener;
import net.cubicworld.game.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;

@SuppressWarnings("unused")
@Getter
public class Plugin extends JavaPlugin implements Listener {
    private Properties pluginSettings;

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        registerEvents(Bukkit.getPluginManager());
        registerCommands();

        try {
            pluginSettings = Config.getConfig("config.properties");
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Unable to get configuration file");
            Bukkit.shutdown();
        }
    }

    private void registerEvents(@NotNull PluginManager pluginManager) {
        pluginManager.registerEvents(new BlockListener(), this);
        pluginManager.registerEvents(new PlayerListener(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("arena")).setExecutor(new ArenaCommand());
    }
}
