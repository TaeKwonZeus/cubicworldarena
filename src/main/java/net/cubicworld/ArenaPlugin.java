package net.cubicworld;

import lombok.Getter;
import net.cubicworld.commands.AbstractCommand;
import net.cubicworld.commands.game.GameCommand;
import net.cubicworld.config.PluginConfig;
import net.cubicworld.database.DataSource;
import net.cubicworld.events.BlockListener;
import net.cubicworld.events.InventoryListener;
import net.cubicworld.events.PlayerListener;
import net.cubicworld.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;

@SuppressWarnings("unused")
@Getter
public class ArenaPlugin extends JavaPlugin implements Listener {
    private PluginConfig pluginConfig;

    private DataSource dataSource;

    private final GameManager gameManager = new GameManager();

    @Override
    public void onEnable() {
        registerEvents(Bukkit.getPluginManager(), new BlockListener(), new InventoryListener(), new PlayerListener());
        registerCommands(new GameCommand());

        try {
            pluginConfig = PluginConfig.load();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Unable to get configuration files", e);
            Bukkit.shutdown();
            return;
        }

        try {
            dataSource = new DataSource(pluginConfig.getDatabase());
            dataSource.validateConnection();
        } catch (SQLException e) {
            getLogger().log(Level.SEVERE, "Unable to connect to database", e);
            Bukkit.shutdown();
            return;
        }

        World world = Objects.requireNonNull(Bukkit.getWorld("world"));
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(6000);
    }

    private void registerEvents(@NotNull PluginManager pluginManager, Listener @NotNull ... listeners) {
        for (Listener listener : listeners)
            pluginManager.registerEvents(listener, this);
    }

    private void registerCommands(AbstractCommand @NotNull ... commands) {
        for (AbstractCommand command : commands) {
            Objects.requireNonNull(getCommand(command.getName())).setExecutor(command);
        }
    }

    public static @NotNull ArenaPlugin getInstance() {
        return JavaPlugin.getPlugin(ArenaPlugin.class);
    }
}
