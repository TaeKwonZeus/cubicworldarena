package net.cubicworld.config;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import lombok.Value;
import net.cubicworld.ArenaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

@Value
public class PluginConfig {
    Database database;

    public static @NotNull PluginConfig load() throws IOException {
        ArenaPlugin plugin = ArenaPlugin.getInstance();

        try(InputStream databaseStream = plugin.getResource("database.properties")) {
            JavaPropsMapper databaseMapper = new JavaPropsMapper();

            return new PluginConfig(databaseMapper.readValue(databaseStream, Database.class));
        }
    }
}
