package net.cubicworld.commands.game;

import lombok.RequiredArgsConstructor;
import net.cubicworld.ArenaPlugin;
import net.cubicworld.commands.ArenaCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor
public class GameListSubcommand implements ArenaCommand {
    private final ArenaPlugin plugin;

    @Override
    public void execute(@NotNull Player player, @NotNull List<String> args) {
        player.sendMessage(Component.text(String.join(" ", plugin.getGameManager().getGames().keySet())));
    }

    @Override
    public @NotNull String getName() {
        return "list";
    }
}
