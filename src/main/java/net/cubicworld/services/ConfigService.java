package net.cubicworld.services;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RequiredArgsConstructor
public class ConfigService {
    private final String path;

    public @NotNull Properties getConfig() throws IOException {
        try(final InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(path)) {
            final Properties properties = new Properties();
            properties.load(stream);
            return properties;
        }
    }
}
