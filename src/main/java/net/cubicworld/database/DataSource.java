package net.cubicworld.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.cubicworld.config.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private final HikariDataSource dataSource;

    public DataSource(@NotNull Database properties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getUrl());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        dataSource = new HikariDataSource(config);
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void validateConnection() throws SQLException {
        try (Connection connection = getConnection()) {
            if (!connection.isValid(1)) throw new SQLException("Connection is invalid");
        }
    }
}
