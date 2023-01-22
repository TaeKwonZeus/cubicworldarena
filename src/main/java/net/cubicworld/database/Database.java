package net.cubicworld.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private final HikariDataSource dataSource;

    public Database(Properties properties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));
        dataSource = new HikariDataSource(config);
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void validateConnection() throws SQLException {
        try(Connection connection = getConnection()) {
            if (!connection.isValid(1)) throw new SQLException("Connection is invalid");
        }
    }
}
