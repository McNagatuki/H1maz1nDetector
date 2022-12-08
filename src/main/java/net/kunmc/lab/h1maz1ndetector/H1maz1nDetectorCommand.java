package net.kunmc.lab.h1maz1ndetector;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.configlib.ConfigCommand;

public class H1maz1nDetectorCommand extends Command {
    public H1maz1nDetectorCommand(ConfigCommand configCommand) {
        super("h1maz1n_detector");
        addChildren(configCommand);
    }
}
