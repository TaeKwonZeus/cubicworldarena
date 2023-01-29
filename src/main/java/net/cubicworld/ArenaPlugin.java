package net.cubicworld;

import lombok.Getter;
import net.cubicworld.commands.ArenaCommand;
import net.cubicworld.database.Database;
import net.cubicworld.events.BlockListener;
import net.cubicworld.events.InventoryListener;
import net.cubicworld.events.PlayerListener;
import net.cubicworld.game.ArenaImpl;
import net.cubicworld.matchmaking.LobbyManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;

@SuppressWarnings("unused")
@Getter
public class ArenaPlugin extends JavaPlugin implements Listener {
    private Properties pluginSettings;

    private Database database;

    private final LobbyManager<ArenaImpl> lobbyManager = new LobbyManager<>();

    @Override
    public void onEnable() {
        registerEvents(Bukkit.getPluginManager(), new BlockListener(), new InventoryListener(), new PlayerListener());
        registerCommands(Map.ofEntries(Map.entry("arena", new ArenaCommand())));

        try {
            pluginSettings = Config.getConfig("config.properties");
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Unable to get configuration file");
            Bukkit.shutdown();
        }

        try {
            database = new Database(pluginSettings);
            database.validateConnection();
        } catch (SQLException e) {
            getLogger().log(Level.SEVERE, "Unable to connect to database", e);
            Bukkit.shutdown();
        }

        World world = Objects.requireNonNull(Bukkit.getWorld("world"));
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(6000);
    }

    private void registerEvents(@NotNull PluginManager pluginManager, Listener... listeners) {
        for (Listener listener : listeners)
            pluginManager.registerEvents(listener, this);
    }

    private void registerCommands(Map<String, CommandExecutor> commandExecutorMap) {
        for (Map.Entry<String, CommandExecutor> entry : commandExecutorMap.entrySet()) {
            Objects.requireNonNull(getCommand(entry.getKey())).setExecutor(entry.getValue());
        }
    }

    public static @NotNull ArenaPlugin getInstance() {
        return JavaPlugin.getPlugin(ArenaPlugin.class);
    }
}
