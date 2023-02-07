package net.cubicworld.commands.game;

import lombok.RequiredArgsConstructor;
import net.cubicworld.ArenaPlugin;
import net.cubicworld.commands.ArenaCommand;
import net.cubicworld.game.Game;
import net.cubicworld.game.GameManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor
public class GameJoinSubcommand implements ArenaCommand {
    private final ArenaPlugin plugin;

    @Override
    public void execute(@NotNull Player player, @NotNull List<String> args) {
        if (args.isEmpty()) {
            player.sendMessage(Component.text("Specify the name of the game", NamedTextColor.RED));
            return;
        }
        String name = args.get(0);

        Bukkit.getScheduler().runTask(plugin, () -> {
            GameManager gameManager = plugin.getGameManager();
            Game game = gameManager.getGame(name);
            if (game == null) {
                player.sendMessage(Component.text("Game with this name doesn't exist", NamedTextColor.RED));
                return;
            }

            player.sendMessage(Component.text("Joining game...", NamedTextColor.GREEN));
            game.addPlayer(player);
        });
    }

    @Override
    public @NotNull String getName() {
        return "join";
    }
}
