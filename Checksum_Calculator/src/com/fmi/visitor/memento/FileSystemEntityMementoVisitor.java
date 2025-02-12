package com.fmi.visitor.memento;

import com.fmi.visitor.FileSystemEntityVisitor;
import org.jetbrains.annotations.NotNull;

public interface FileSystemEntityMementoVisitor extends FileSystemEntityVisitor {
    boolean hasFinished();

    boolean isStopped();

    void pause();

    ProcessedFilesSnapshot getSnapshot();

    void restore(@NotNull ProcessedFilesSnapshot snapshot);
}
