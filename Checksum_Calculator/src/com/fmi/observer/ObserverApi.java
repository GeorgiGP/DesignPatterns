package com.fmi.observer;

import org.jetbrains.annotations.NotNull;

public interface ObserverApi {
    void update(@NotNull ObservableApi sender, @NotNull FileMessage message);
}