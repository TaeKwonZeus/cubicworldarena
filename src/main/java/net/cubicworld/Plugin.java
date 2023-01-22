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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cubicworld?user=cubicworld&password=111111");
            connection.close();
        } catch (SQLException e) {
            getLogger().log(Level.SEVERE, "Unable to connect to database", e);
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
