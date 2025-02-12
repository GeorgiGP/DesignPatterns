package com.fmi.visitor;

import com.fmi.filesystem.Directory;
import com.fmi.filesystem.Shortcut;
import com.fmi.filesystem.SingleFile;
import org.jetbrains.annotations.NotNull;

public interface FileSystemEntityVisitor {
    void visit(@NotNull SingleFile file);

    void visit(@NotNull Directory directory);

    void visit(@NotNull Shortcut file);
}
