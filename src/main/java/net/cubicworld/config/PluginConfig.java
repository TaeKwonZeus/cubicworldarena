package net.cubicworld.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import lombok.Value;
import lombok.experimental.NonFinal;
import net.cubicworld.ArenaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

@Value
public class PluginConfig {
    @NonFinal
    Database database;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PluginConfig() {

    }

    public static @NotNull PluginConfig load() throws IOException {
        ArenaPlugin plugin = ArenaPlugin.getInstance();

        try(InputStream configStream = plugin.getResource("config.json");
            InputStream databaseStream = plugin.getResource("database.properties")) {
            ObjectMapper configMapper = new ObjectMapper();
            JavaPropsMapper databaseMapper = new JavaPropsMapper();

            PluginConfig config = configMapper.readValue(configStream, PluginConfig.class);
            config.database = databaseMapper.readValue(databaseStream, Database.class);

            return config;
        }
    }
}
