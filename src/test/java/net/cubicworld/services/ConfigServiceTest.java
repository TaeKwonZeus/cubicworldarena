package net.cubicworld.services;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

class ConfigServiceTest {
    @Test
    void getConfig() throws IOException {
        final Properties properties = ConfigService.getConfig();
        assert !properties.isEmpty();
    }
}