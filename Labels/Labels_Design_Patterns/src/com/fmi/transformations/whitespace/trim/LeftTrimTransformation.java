package com.fmi.transformations.whitespace.trim;

import org.jetbrains.annotations.NotNull;

public class LeftTrimTransformation implements TrimTransformation {
    @Override
    public String transform(@NotNull String text) {
        return text.stripLeading();
    }
}
