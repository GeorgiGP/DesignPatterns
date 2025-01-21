package com.fmi.transformations.replace.censor;

import com.fmi.transformations.TextTransformation;
import org.jetbrains.annotations.NotNull;

public class CensorTransformation implements TextTransformation {
    @NotNull
    private final String censoredText;

    public CensorTransformation(@NotNull String censorText) {
        this.censoredText = censorText;
    }

    @Override
    public String transform(@NotNull String text) {
        return text.replaceAll(censoredText, "*".repeat(censoredText.length()));
    }

    @NotNull
    public String getCensoredText() {
        return censoredText;
    }
}
