package factory;

import exceptions.FailedFigureCreationException;
import figures.Circle;
import figures.Figure;
import figures.HexagonTest;
import figures.Rectangle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringToFigureTest {
    @Test
    void testCircleCreateFromStringValid()  {
        String toStr = "circle 2.2124142";

        StringToFigure factoryFigure = new StringToFigure();
        Figure extracted = factoryFigure.createFrom(toStr);
        assertEquals(toStr, extracted.toString(), "Must be valid with" + toStr);
    }

    @Test
    void testCircleCreateFromStringNoRadius() {
        String toStr = "circle";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class ,() -> factoryFigure.createFrom(toStr),
                "Must take some parameters!");
    }

    @Test
    void testCircleCreateFromStringTwoParameters() {
        String toStr = "circle 23123.33 12312.2";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class ,() -> factoryFigure.createFrom(toStr),
                "Circle must have only one parameter!");
    }

    @Test
    void testCircleCreateFromStringWhiteSpaces() {
        String toStr = "    circle     23123.33    ";
        BigDecimal radius = BigDecimal.valueOf(23123.33);
        Figure circle = new Circle(radius);

        StringToFigure factoryFigure = new StringToFigure();
        assertEquals(factoryFigure.createFrom(toStr).toString(), circle.toString(),
                "White spaces should not effect the creating of figure!");
        assertNotEquals(factoryFigure.createFrom(toStr).toString(), toStr,
                "Can extract from string with white spaces but does not mean toString returns the same string!");
    }

    @Test
    void testCircleCreateFromStringNonDigitSymbols() {
        String toStr = "circle 23a214";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class ,() -> factoryFigure.createFrom(toStr),
                "When extracting radius parameter should be valid BigDecimal!");
    }


    @Test
    void testRectangleCreateFromStringValid() {
        String toStr = "rectangle 2.2124142 4.12";

        StringToFigure factoryFigure = new StringToFigure();
        Figure extracted = factoryFigure.createFrom(toStr);
        assertEquals(toStr, extracted.toString(), "Must be equal!");
    }

    @Test
    void testRectangleCreateFromStringNoParameters() {
        String toStr = "rectangle ";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class ,() -> factoryFigure.createFrom(toStr),
                "Must take some parameters!");
    }

    @Test
    void testRectangleCreateFromStringOneParameters() {
        String toStr = "rectangle 23123.33";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class ,() -> factoryFigure.createFrom(toStr),
                "Rectangle must have 2 parameters!");
    }

    @Test
    void testRectangleCreateFromStringWhiteSpaces() {
        String toStr = "    rectangle     23123.33   233                 ";
        BigDecimal sideA = BigDecimal.valueOf(23123.33);
        BigDecimal sideB = BigDecimal.valueOf(233);
        Figure rectangle = new Rectangle(sideA, sideB);

        StringToFigure factoryFigure = new StringToFigure();
        assertEquals(factoryFigure.createFrom(toStr).toString(), rectangle.toString(),
                "White spaces should not effect the creating of figure!");
        assertNotEquals(factoryFigure.createFrom(toStr).toString(), toStr,
                "Can extract from string with white spaces but does not mean toString returns the same string!");
    }

    @Test
    void testRectangleCreateFromStringWithNonDigitSymbols() {
        String toStr = "rectangle 23a214 12412";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class ,() -> factoryFigure.createFrom(toStr),
                "When extracting radius parameter should be valid BigDecimal!");
    }

    @Test
    void testTriangleCreateFromStringValid() {
        String toStr = "triangle 2.2124142 4.12 3";

        StringToFigure factoryFigure = new StringToFigure();
        Figure extracted = factoryFigure.createFrom(toStr);
        assertEquals(toStr, extracted.toString(), "Must be equal!");
    }

    @Test
    void testUnknownCreateFromString() {
        String toStr = "unknown 2.2124142 4.12 3 2";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class, ()-> factoryFigure.createFrom(toStr),
                "unknown type does not exist!");
    }

    @Test
    void testDoubleCreateFromString() {
        String toStr = "double 2";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class, ()-> factoryFigure.createFrom(toStr),
                "Double class not found in the same directory as Figure directory!");
    }

    @Test
    void testFigureCreateFromString() {
        String toStr = "figure 2 4 3";

        StringToFigure factoryFigure = new StringToFigure();
        assertThrows(FailedFigureCreationException.class, ()-> factoryFigure.createFrom(toStr),
                "Creating instance of Figure should fail");
    }

    @Test
    void testTestFigureCreateFromString() {
        String str = "hexagon 2 2.5 3 3 2.5 2";
        BigDecimal sideA = BigDecimal.valueOf(2);
        BigDecimal sideB = BigDecimal.valueOf(2.5);
        BigDecimal sideC = BigDecimal.valueOf(3);
        StringToFigure factoryFigure = new StringToFigure();
        Figure hexagon = new HexagonTest(sideA, sideB, sideC, sideC, sideB, sideA);
        assertDoesNotThrow(hexagon::toString,
                "toString works with new figures without overriding the function!");
        assertThrows(FailedFigureCreationException.class, () -> factoryFigure.createFrom(str),
                "If there is a subclass of Figure it should be in the same directory!");
    }
}
