package net.cubicworld.services;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

class ConfigServiceTest {

    @Test
    void getTestConfig() throws IOException {
        Properties properties = ConfigService.getTestConfig();
        assert !properties.isEmpty();
    }

    @Test
    void getConfig() throws IOException {
        Properties properties = ConfigService.getConfig();
        assert !properties.isEmpty();
    }
}