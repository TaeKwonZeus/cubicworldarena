package net.cubicworld.database;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

public class Database {
    private final Properties properties;

    public Database(Properties properties) {
        this.properties = properties;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().log(Level.SEVERE, "JDBC driver not found", e);
        }
    }

    public @NotNull Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password"));
    }

    public boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection.isValid(1);
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Unable to connect to database", e);
            return false;
        }
    }
}
