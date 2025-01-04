package com.fmi.labels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RichLabelTest {
    @Test
    public void testTextShouldReturnCorrectly()
    {
        // Arrange
        var expectedText = "test";
        var expectedColor = "red";
        var expectedFont = "Arial";
        int expectedFontSize = 16;
        Label label = new RichLabel(expectedText, expectedFontSize, expectedFont, expectedColor);

        // Act
        var actualText = label.getText();

        // Assert
        assertTrue(actualText.contains(expectedText));
        assertTrue(actualText.contains(expectedColor));
        assertTrue(actualText.contains(expectedFont));
        assertTrue(actualText.contains(Integer.toString(expectedFontSize)));
    }
}
