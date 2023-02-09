package net.cubicworld.commands.game;

import lombok.RequiredArgsConstructor;
import net.cubicworld.ArenaPlugin;
import net.cubicworld.commands.ArenaCommand;
import net.cubicworld.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor
public class GameLeaveSubcommand implements ArenaCommand {
    private final ArenaPlugin plugin;

    @Override
    public void execute(@NotNull Player player, @NotNull List<String> args) {
        Game game = plugin.getGameManager().getGame(player);
        if (game == null) {
            player.sendMessage(Component.text("You currently aren't in a game!", NamedTextColor.RED));
            return;
        }

        game.removePlayer(player);
    }

    @Override
    public @NotNull String getName() {
        return "leave";
    }
}
