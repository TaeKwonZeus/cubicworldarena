package net.cubicworld;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static @NotNull Properties getConfig(String path) throws IOException {
        try(final InputStream stream = JavaPlugin.getPlugin(Plugin.class).getResource(path)) {
            final Properties properties = new Properties();
            properties.load(stream);
            return properties;
        }
    }
}
