package com.fmi.reporter;

import com.fmi.observer.ObserverApi;

public interface ProgressReporterApi extends ObserverApi {
    void startTimer(long expectedTotalBytes);

    long endTimer();
}
