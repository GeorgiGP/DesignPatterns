package com.fmi.decorator;

import com.fmi.labels.Label;
import com.fmi.transformations.TextTransformation;
import org.jetbrains.annotations.NotNull;

public class LabelDecoratorBase implements Label {
    @NotNull
    private Label label;

    public LabelDecoratorBase(@NotNull Label label) {
        this.label = label;
    }

    @Override
    public String getText() {
        return label.getText();
    }

    public static Label removeDecoratorFrom(@NotNull Label label,
                                            @NotNull Class<? extends LabelDecoratorBase> decoratorClass) {
        if (label instanceof LabelDecoratorBase) {
            return ((LabelDecoratorBase) label).removeDecorator(decoratorClass);
        }
        return label;
    }

    public Label removeDecorator(@NotNull Class<? extends LabelDecoratorBase> decoratorType) {
        if (this.getClass().equals(decoratorType)) {
            return label;
        }
        if (!(label instanceof LabelDecoratorBase)) {
            return this;
        }
        label = ((LabelDecoratorBase) label).removeDecorator(decoratorType);
        return this;
    }

    public static Label removeTransformationFrom(@NotNull Label label,
                                            @NotNull Class<? extends TextTransformation> transformationClass) {
        if (label instanceof LabelDecoratorBase) {
            return ((LabelDecoratorBase) label).removeTransformation(transformationClass);
        }
        return label;
    }

    public Label removeTransformation(@NotNull Class<? extends TextTransformation> transformationClass) {
        if (this.getClass().equals(TextTransformationDecorator.class) &&
                ((TextTransformationDecorator)this).getTextTransformation().getClass().equals(transformationClass)) {
            return label;
        }
        if (!(label instanceof LabelDecoratorBase)) {
            return this;
        }
        label = ((LabelDecoratorBase) label).removeTransformation(transformationClass);
        return this;
    }
}
