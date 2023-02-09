package net.cubicworld.commands.game;

import net.cubicworld.ArenaPlugin;
import net.cubicworld.commands.AbstractCommand;
import net.cubicworld.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameCommand extends AbstractCommand {
    private final ArenaPlugin plugin;

    public GameCommand(ArenaPlugin plugin) {
        super(
                new GameCreateSubcommand(plugin), new GameJoinSubcommand(plugin),
                new GameLeaveSubcommand(plugin), new GameListSubcommand(plugin));

        this.plugin = plugin;
    }

    @Override
    public void execute(@NotNull Player player, @NotNull List<String> args) {
        Game game = plugin.getGameManager().getGame(player);
        if (game == null) {
            player.sendMessage(Component.text("You currently aren't in a game!", NamedTextColor.RED));
            return;
        }

        player.sendMessage(Component.text(game.getName(), NamedTextColor.GREEN));
    }

    @Override
    public @NotNull String getName() {
        return "game";
    }
}
