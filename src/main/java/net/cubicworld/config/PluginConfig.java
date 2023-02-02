package net.cubicworld.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Getter
@RequiredArgsConstructor
public class PluginConfig {
    private final Database database;

    public static @NotNull PluginConfig load() {
        return new PluginConfig(new Database(
                System.getenv("DB_URL"),
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD")
        ));
    }
}
