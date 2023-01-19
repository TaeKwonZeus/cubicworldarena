package net.cubicworld.services;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

class DatabaseServiceTest {
    @Test
    void getConnection() throws SQLException, IOException {
        try(Connection connection = DatabaseService.getConnection(ConfigService.getTestConfig())) {
            assert connection.isValid(0);
            assert !connection.isClosed();
        }
    }
}