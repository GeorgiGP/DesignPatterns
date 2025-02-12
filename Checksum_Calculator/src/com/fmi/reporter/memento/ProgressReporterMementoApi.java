package com.fmi.reporter.memento;

import com.fmi.reporter.ProgressReporterApi;
import org.jetbrains.annotations.NotNull;

public interface ProgressReporterMementoApi extends ProgressReporterApi {
    boolean isStopped();

    void pause();

    ProgressSnapshot getSnapshot();

    void restore(@NotNull ProgressSnapshot snapshot);
}
