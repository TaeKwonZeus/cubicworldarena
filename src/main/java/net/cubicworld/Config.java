package net.cubicworld;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@AllArgsConstructor
public class Config {
    public static @NotNull Properties getConfig(String path) throws IOException {
        try(final InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(path)) {
            final Properties properties = new Properties();
            properties.load(stream);
            return properties;
        }
    }
}
