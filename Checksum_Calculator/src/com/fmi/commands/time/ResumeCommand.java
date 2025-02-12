package com.fmi.commands.time;

import com.fmi.commands.calculate.ModeCommand;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ResumeCommand extends TimeCommand {
    public ResumeCommand(@NotNull Set<@NotNull ModeCommand> commands) {
        super(commands);
    }

    public ResumeCommand() {
        super();
    }

    @Override
    public void execute() {
        for (ModeCommand currentCalculate : commands) {
            if (!currentCalculate.hasFinished()) {
                currentCalculate.resumeProcessing();
            } else {
                commands.remove(currentCalculate);
            }
        }
    }
}
