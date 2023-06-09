package de.tamion.other;

import de.tamion.ConsoleUser;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "ConsolePluginConsoleAppender", category = "Core", elementType = "appender", printObject = true)
public class ConsoleAppender extends AbstractAppender {
    public ConsoleAppender() {
        super("ConsolePluginConsoleAppender", null, PatternLayout.newBuilder().withPattern("[%d{HH:mm:ss} %level]: %msg").build());
    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public void append(LogEvent e) {
        ConsoleUser.consoleusers.forEach(player -> player.sendMessage(e.getMessage().getFormattedMessage()));
        ConsoleUser.tempconsoleusers.forEach(player -> player.sendMessage(e.getMessage().getFormattedMessage()));
    }
}