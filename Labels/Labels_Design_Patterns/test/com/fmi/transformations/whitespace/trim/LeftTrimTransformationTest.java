package com.fmi.transformations.whitespace.trim;

import com.fmi.transformations.CapitalizeTransformation;
import com.fmi.transformations.TextTransformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeftTrimTransformationTest {
    private final TextTransformation textTransformation = new LeftTrimTransformation();
    @Test
    void testLeftTrimTransformation() {

        assertEquals(textTransformation.transform("   Word  one"), "Word  one");
        assertEquals(textTransformation.transform("  word two  "), "word two  ");
    }
}
