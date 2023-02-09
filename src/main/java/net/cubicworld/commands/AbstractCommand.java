package net.cubicworld.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.stream.Collectors;

public abstract class AbstractCommand implements ArenaCommand, CommandExecutor {
    private final Map<String, ArenaCommand> subcommands;

    protected AbstractCommand(@NotNull ArenaCommand... subcommands) {
        this.subcommands = Arrays.stream(subcommands)
                .collect(Collectors.toMap(ArenaCommand::getName, Function.identity()));
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            if (!(sender instanceof Player player)) return false;

            List<String> argsList = Arrays.asList(args);

            if (argsList.isEmpty() || !subcommands.containsKey(argsList.get(0))) {
                execute(player, argsList);
                return true;
            }

            subcommands.get(argsList.get(0)).execute(player, argsList.stream().skip(1).toList());
            return true;
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.WARNING, "Unexpected error while parsing command" + getName(), e);
            return false;
        }
    }
}
