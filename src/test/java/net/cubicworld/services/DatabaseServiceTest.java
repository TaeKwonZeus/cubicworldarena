package net.cubicworld.services;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

class DatabaseServiceTest {
    @Test
    void getConnection() throws Exception {
        try(final Connection connection = DatabaseService.getConnection(ConfigService.getConfig())) {
            assert connection.isValid(0);
            assert !connection.isClosed();
        }
    }
}
