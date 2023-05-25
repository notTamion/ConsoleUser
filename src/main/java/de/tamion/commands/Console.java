package de.tamion.commands;

import de.tamion.ConsoleUser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
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
            if (!ConsoleUser.consoleusers.contains(p)) {
                ConsoleUser.tempconsoleusers.add(p);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.join(" ", args));
                p.sendMessage("Dispatched Command");
                BukkitScheduler scheduler = Bukkit.getScheduler();
                scheduler.runTaskLater(ConsoleUser.getPlugin(), () -> {
                    ConsoleUser.tempconsoleusers.remove(p);
                }, 20L);
            } else {
                p.sendMessage("Please just write out the command");
                return false;
            }
        }
        return true;
    }
}
