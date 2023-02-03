package net.cubicworld.commands.game;

import net.cubicworld.commands.AbstractCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameCommand extends AbstractCommand {
    public GameCommand() {
        super(new GameJoinSubcommand());
    }

    @Override
    public void execute(@NotNull Player player, @NotNull List<String> args) {

    }

    @Override
    public @NotNull String getName() {
        return "game";
    }
}
