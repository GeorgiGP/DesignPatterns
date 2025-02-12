package com.fmi.commands.calculate;

import com.fmi.commands.Command;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;

public interface ModeCommand extends Command {
    void execute(@NotNull OutputStream fillStream) throws IOException;

    void resumeProcessing();

    void pauseProcessing();

    boolean hasFinished();

}
