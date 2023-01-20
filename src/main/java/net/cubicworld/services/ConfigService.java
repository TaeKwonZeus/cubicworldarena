package net.cubicworld.services;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigService {
    public static @NotNull Properties getConfig() throws IOException {
        try(final InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties")) {
            final Properties properties = new Properties();
            properties.load(stream);
            return properties;
        }
    }
}