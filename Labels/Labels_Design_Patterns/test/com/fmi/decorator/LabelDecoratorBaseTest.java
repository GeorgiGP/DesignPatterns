package com.fmi.decorator;

import com.fmi.labels.Label;
import com.fmi.transformations.CapitalizeTransformation;
import com.fmi.transformations.DecorateTransformation;
import com.fmi.transformations.TextTransformation;
import com.fmi.transformations.composite.CompositeTransformation;
import com.fmi.transformations.whitespace.trim.LeftTrimTransformation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LabelDecoratorBaseTest {
    private static LabelDecoratorBase decorator;
    private static Label mocked;
    private static List<TextTransformation> transformations;
    private static CyclingTransformationsDecorator cyclingTransformationsDecorator;

    @BeforeAll
    static void setUp() {
        TextTransformation tr1 = mock(LeftTrimTransformation.class);
        TextTransformation tr2 = mock(CapitalizeTransformation.class);
        TextTransformation tr3 = mock(DecorateTransformation.class);

        when(tr1.transform(" testing word")).thenReturn("testing word");
        when(tr2.transform("testing word")).thenReturn("Testing word");
        when(tr2.transform(" testing word")).thenReturn(" testing word");
        when(tr3.transform("Testing word")).thenReturn("-={ Testing word }=-");
        when(tr3.transform("testing word")).thenReturn("-={ testing word }=-");
        when(tr3.transform(" testing word")).thenReturn("-={  testing word }=-");

        transformations = List.of(tr1, tr2, tr3);
        mocked = mock();
        when(mocked.getText()).thenReturn(" testing word");

        cyclingTransformationsDecorator = new CyclingTransformationsDecorator(mocked, transformations);

        decorator = new LabelDecoratorBase(mocked);
    }

    @Test
    void testRemovingConcreteTextDecorators() {
        Label toDecorate = mocked; // " testing word"
        when(toDecorate.getText()).thenReturn(" testing word");
        toDecorate = new TextTransformationDecorator(toDecorate, transformations.get(0));
        assertEquals("testing word", toDecorate.getText());
        toDecorate = new TextTransformationDecorator(toDecorate, transformations.get(1));
        assertEquals("Testing word", toDecorate.getText());
        toDecorate = new TextTransformationDecorator(toDecorate, transformations.get(2));
        assertEquals("-={ Testing word }=-", toDecorate.getText());

        toDecorate = LabelDecoratorBase.removeTransformationFrom(toDecorate, CapitalizeTransformation.class);
        assertEquals("-={ testing word }=-", toDecorate.getText());

        toDecorate = LabelDecoratorBase.removeTransformationFrom(toDecorate, LeftTrimTransformation.class);
        assertEquals("-={  testing word }=-", toDecorate.getText());

        toDecorate = LabelDecoratorBase.removeTransformationFrom(toDecorate, CompositeTransformation.class);
        assertEquals("-={  testing word }=-", toDecorate.getText());

        toDecorate = LabelDecoratorBase.removeTransformationFrom(toDecorate, DecorateTransformation.class);
        assertEquals(" testing word", toDecorate.getText());

        //now should return at the moment because toDecorate is not LabelDecoratorBase, its Label
        toDecorate = LabelDecoratorBase.removeTransformationFrom(toDecorate, DecorateTransformation.class);
        assertEquals(" testing word", toDecorate.getText());
    }

    @Test
    void testRemovingDecorators() {
        Label toDecorate = mocked; // " testing word"
        when(toDecorate.getText()).thenReturn(" testing word");

        toDecorate = new TextTransformationDecorator(toDecorate, transformations.get(0));
        assertEquals("testing word", toDecorate.getText());

        toDecorate = new CyclingTransformationsDecorator(toDecorate, List.of(transformations.get(2)));
        assertEquals("-={ testing word }=-", toDecorate.getText());

        toDecorate = LabelDecoratorBase.removeTransformationFrom(toDecorate, CapitalizeTransformation.class);
        assertEquals("-={ testing word }=-", toDecorate.getText());

        toDecorate = LabelDecoratorBase.removeDecoratorFrom(toDecorate, TextTransformationDecorator.class);
        assertEquals("-={  testing word }=-", toDecorate.getText());

        toDecorate = LabelDecoratorBase.removeDecoratorFrom(toDecorate, RandomTransformationDecorator.class);
        assertEquals("-={  testing word }=-", toDecorate.getText());

        toDecorate = LabelDecoratorBase.removeDecoratorFrom(toDecorate, CyclingTransformationsDecorator.class);
        assertEquals(" testing word", toDecorate.getText());

        //now should return at the moment because toDecorate is not LabelDecoratorBase, its Label
        toDecorate = LabelDecoratorBase.removeDecoratorFrom(toDecorate, CyclingTransformationsDecorator.class);
        assertEquals(" testing word", toDecorate.getText());
    }
}
