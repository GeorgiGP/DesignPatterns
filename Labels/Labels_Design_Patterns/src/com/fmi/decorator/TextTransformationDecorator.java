package com.fmi.decorator;

import com.fmi.labels.Label;
import com.fmi.transformations.TextTransformation;
import org.jetbrains.annotations.NotNull;

public class TextTransformationDecorator extends LabelDecoratorBase {
    @NotNull
    private final TextTransformation textTransformation;

    public TextTransformationDecorator(@NotNull Label labelDecorator,
                                       @NotNull TextTransformation textTransformation) {
        super(labelDecorator);
        this.textTransformation = textTransformation;
    }

    @NotNull
    public TextTransformation getTextTransformation() {
        return textTransformation;
    }

    @Override
    public String getText() {
        return textTransformation.transform(super.getText());
    }
}
