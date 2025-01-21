package com.fmi.transformations;

import org.jetbrains.annotations.NotNull;

public class CapitalizeTransformation implements TextTransformation {
    @Override
    public String transform(@NotNull String text) {
        char firstChar = text.charAt(0);
        StringBuilder builder = (new StringBuilder(text));
        builder.setCharAt(0, Character.toUpperCase(firstChar));
        return builder.toString();
    }
}
