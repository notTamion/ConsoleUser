package de.tamion.listeners;

import de.tamion.ConsoleUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ConsoleCommand implements Listener {
    @EventHandler
    public void onMessage(PlayerChatEvent e) {
        Player p = e.getPlayer();
        if(!ConsoleUser.consoleusers.contains(p)) {
            return;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), e.getMessage());
        e.setCancelled(true);
    }
}
