package com.fmi.labels;

import org.jetbrains.annotations.NotNull;

public class HelpLabel extends SimpleTextLabel {
    @NotNull
    private final String helpText;

    public HelpLabel(@NotNull String text, @NotNull String helpText) {
        super(text);
        this.helpText = helpText;
    }

    public HelpLabel(@NotNull Label label, @NotNull String helpText) {
        super(label.getText());
        this.helpText = helpText;
    }

    @NotNull
    public String getHelpText() {
        return helpText;
    }
}
