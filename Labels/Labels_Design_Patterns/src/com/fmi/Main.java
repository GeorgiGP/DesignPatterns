package com.fmi;

import com.fmi.decorator.LabelDecoratorBase;
import com.fmi.decorator.TextTransformationDecorator;
import com.fmi.labels.CustomLabelProxy;
import com.fmi.labels.HelpLabel;
import com.fmi.labels.Label;
import com.fmi.labels.RichLabel;
import com.fmi.labels.SimpleTextLabel;
import com.fmi.transformations.TextTransformation;
import com.fmi.transformations.replace.censor.CensorTransformation;
import com.fmi.transformations.replace.ReplaceTransformation;
import com.fmi.transformations.whitespace.NormalizeSpaceTransformation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Label l;
        l = new SimpleTextLabel(" abcd   efgh ijkl mnop      ");
        l = new TextTransformationDecorator( l, new ReplaceTransformation("abcd", ""));
        l = new TextTransformationDecorator( l, new NormalizeSpaceTransformation());

        TextTransformation transformation = new CensorTransformation("efgh");
        l = new TextTransformationDecorator( l, transformation);

        l = new TextTransformationDecorator( l, new CensorTransformation("mnop"));

        System.out.println(l.getText()); // returns "**** ijkl ****";

//        l = LabelDecoratorBase.removeTransformationFrom(l,
//                transformation);
//        System.out.println(l.getText());
//
//        System.out.println(l.getText()); // returns "efgh ijkl ****"
//
//        l = LabelDecoratorBase.removeTransformationClassFrom(l,
//                NormalizeSpaceTransformation.class);
//        System.out.println(l.getText()); // returns "    efgh ijkl ****";
//
//        l = LabelDecoratorBase.removeDecoratorClassFrom(l,
//                TextTransformationDecorator.class);
//        System.out.println(l.getText()); // returns "    efgh ijkl mnop";
//
//        l = LabelDecoratorBase.removeDecoratorClassFrom(l,
//                TextTransformationDecorator.class);
//        System.out.println(l.getText()); //returns " abcd   efgh ijkl mnop      ";

        Label richLabel = new RichLabel(l, 16, "Arial", "red");
        CustomLabelProxy proxy = new CustomLabelProxy(richLabel, 1);
        HelpLabel help = new HelpLabel(proxy, "help");
        while (true) {
            System.out.println(help.getText());
            System.out.println(help.getHelpText());
        }
    }
}
