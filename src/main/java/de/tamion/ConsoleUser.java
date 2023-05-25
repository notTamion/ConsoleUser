package de.tamion;

import de.tamion.commands.Console;
import de.tamion.listeners.ConsoleCommand;
import de.tamion.other.ConsoleAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class ConsoleUser extends JavaPlugin {

    public static ArrayList<Player> consoleusers = new ArrayList<>();
    private static ConsoleUser plugin;
    @Override
    public void onEnable() {
        plugin = this;
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ConsoleCommand(), this);

        getCommand("console").setExecutor(new Console());

        Logger log = (Logger) LogManager.getRootLogger();
        log.addAppender(new ConsoleAppender());
    }

    @Override
    public void onDisable() {
        Logger log = (Logger) LogManager.getRootLogger();
        log.removeAppender(new ConsoleAppender());
    }

    public static ConsoleUser getPlugin() {
        return plugin;
    }
}
