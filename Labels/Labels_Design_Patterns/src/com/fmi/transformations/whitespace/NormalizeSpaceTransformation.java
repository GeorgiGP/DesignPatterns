package com.fmi.transformations.whitespace;

import com.fmi.transformations.TextTransformation;
import org.jetbrains.annotations.NotNull;

public class NormalizeSpaceTransformation implements TextTransformation {
    @Override
    public String transform(@NotNull String text) {
        return text.replaceAll("\\s+", " ").strip();
    }
}
