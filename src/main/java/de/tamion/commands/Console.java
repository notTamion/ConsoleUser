package de.tamion.commands;

import de.tamion.ConsoleUser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Console implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a Player to execute this Command");
            return false;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("ConsoleUser.console")) {
            p.sendMessage("You aren't allowed to execute this Command");
            return false;
        }
        if(args.length == 0) {
            if (ConsoleUser.consoleusers.contains(p)) {
                ConsoleUser.consoleusers.remove(p);
                p.sendMessage("You are no longer a console user");
            } else {
                ConsoleUser.consoleusers.add(p);
                p.sendMessage("You are now a console user");
            }
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.join(" ", args));
            p.sendMessage("Dispatched Command");
        }
        return true;
    }
}
