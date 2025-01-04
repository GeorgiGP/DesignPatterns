package com.fmi.labels;

import org.jetbrains.annotations.NotNull;

public class SimpleTextLabel implements Label {
    @NotNull
    protected final String text;

    public SimpleTextLabel(@NotNull String text) {
        this.text = text;
    }

    @NotNull
    @Override
    public String getText() {
        return text;
    }
}
