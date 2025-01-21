package com.fmi;

import com.fmi.decorator.LabelDecoratorBase;
import com.fmi.decorator.TextTransformationDecorator;
import com.fmi.labels.Label;
import com.fmi.labels.SimpleTextLabel;
import com.fmi.transformations.replace.censor.CensorTransformation;
import com.fmi.transformations.replace.ReplaceTransformation;
import com.fmi.transformations.whitespace.NormalizeSpaceTransformation;

public class Main {
    public static void main(String[] args) {
        Label l;
        l = new SimpleTextLabel("    abcd   efgh ijkl mnop      ");
        l = new TextTransformationDecorator( l, new ReplaceTransformation("abcd", ""));
        l = new TextTransformationDecorator( l, new NormalizeSpaceTransformation());
        l = new TextTransformationDecorator( l, new CensorTransformation("mnop"));

        System.out.println(l.getText()); // returns "efgh ijkl ****"

        l = LabelDecoratorBase.removeTransformationClassFrom(l,
                NormalizeSpaceTransformation.class);
        System.out.println(l.getText()); // returns "   efgh ijkl ****";

        l = LabelDecoratorBase.removeDecoratorClassFrom(l,
                TextTransformationDecorator.class);
        System.out.println(l.getText()); // returns "    efgh ijkl mnop";

        l = LabelDecoratorBase.removeDecoratorClassFrom(l,
                TextTransformationDecorator.class);
        System.out.println(l.getText()); //returns " abcd efgh ijkl mnop";

    }
}
