package net.cubicworld.services;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

class ConfigServiceTest {
    @Test
    void getConfig() throws IOException {
        final Properties properties = new ConfigService("config.properties").getConfig();
        assert !properties.isEmpty();
    }
}
