package net.cubicworld.database;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@AllArgsConstructor
public class Database {
    private final Properties properties;

    public @NotNull Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password"));
    }

    public boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection.isValid(1);
        } catch (SQLException e) {
            return false;
        }
    }
}
