package net.cubicworld.commands;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ArenaCommand {
    void execute(@NotNull Player player, @NotNull List<String> args);

    @NotNull String getName();
}
