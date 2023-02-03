package net.cubicworld.commands.game;

import net.cubicworld.commands.ArenaCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameJoinSubcommand implements ArenaCommand {
    @Override
    public void execute(@NotNull Player player, @NotNull List<String> args) {

    }

    @Override
    public @NotNull String getName() {
        return "join";
    }
}
