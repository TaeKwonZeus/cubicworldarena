package net.cubicworld.commands;

import net.cubicworld.ArenaPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Sender isn't an instance of Player"));
            return false;
        }

        if (args[0].equals("create")) {
            ArenaPlugin.getInstance().getLobbyManager().createAndJoinLobby(args[1], player);
        } else if (args[0].equals("join")) {
            ArenaPlugin.getInstance().getLobbyManager().joinLobby(args[1], player);
        }

        return true;
    }
}
