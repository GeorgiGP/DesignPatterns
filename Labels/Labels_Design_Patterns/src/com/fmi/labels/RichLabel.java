package com.fmi.labels;

import org.jetbrains.annotations.NotNull;

public class RichLabel extends SimpleTextLabel {
    private final int fontSize;
    @NotNull
    private final String fontFamily;
    @NotNull
    private final String color;

    public RichLabel(@NotNull String text, int fontSize, @NotNull String fontFamily, @NotNull String color) {
        super(text);
        this.fontSize = fontSize;
        this.fontFamily = fontFamily;
        this.color = color;
    }

    @NotNull
    @Override
    public String getText() {
        return "<html><font color=\"" + color +
                "\" face=\"" + fontFamily +
                "\" size=\"" + fontSize + "\">" + text + "</font></html>";
    }
}
