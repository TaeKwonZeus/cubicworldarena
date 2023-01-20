package net.cubicworld.services;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@RequiredArgsConstructor
public class DatabaseService {
    private final Properties properties;

    public @NotNull Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password"));
    }
}
