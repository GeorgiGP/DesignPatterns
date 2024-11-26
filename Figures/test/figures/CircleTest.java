package figures;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {
    private static final double EPSION = 1e-15;

    private BigDecimal calculatePerimeterTesting(BigDecimal radius) {
        return radius.multiply(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(Math.PI));
    }

    @Test
    void testCalculatePerimeterNormal() {
        BigDecimal radius = BigDecimal.valueOf(5);
        Figure circle = new Circle(radius);

        BigDecimal perimeter = circle.calculatePerimeter();
        BigDecimal result = calculatePerimeterTesting(radius);

        assertEquals(perimeter, result, "Formula should be 2*PI*r");
    }

    @Test
    void testCalculatePerimeterNormalCloseToZero() {
        BigDecimal radius = BigDecimal.valueOf(EPSION);
        Figure circle = new Circle(radius);

        BigDecimal perimeter = circle.calculatePerimeter();
        BigDecimal result = calculatePerimeterTesting(radius);

        assertEquals(perimeter, result, "Formula should be 2*PI*r");
    }

    @Test
    void testOverflowDoubleMaxRadius() {
        assertThrows(ArithmeticException.class , () -> new Circle(BigDecimal.valueOf(Double.MAX_VALUE)),
                "Perimeter should overflow if radius Max double!");
    }

    @Test
    void testOverflowHalfMaxRadius() {
        BigDecimal radius = BigDecimal.valueOf(Double.MAX_VALUE)
                .divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP);

        assertThrows(ArithmeticException.class , () -> new Circle(radius),
                "Perimeter should overflow if radius Max double / 2!");
    }

    @Test
    void testNegativeRadius() {
        BigDecimal radius = BigDecimal.valueOf(-1);
        assertThrows(IllegalArgumentException.class , () -> new Circle(radius),
                "No negative radius!");
    }

    @Test
    void testZeroRadius() {
        BigDecimal radius = BigDecimal.ZERO;
        assertThrows(IllegalArgumentException.class , () -> new Circle(radius),
                "Radius should not be zero!");
    }

    @Test
    void testToStringCircle() {
        BigDecimal radius = BigDecimal.valueOf(2.2124142);
        Figure circle = new Circle(radius);
        assertEquals("circle 2.2124142", circle.toString(),
                "Name of the class in string must be with lower case, name and radius separated with \" \" and radius should save the exact value!");
    }

    @Test
    void testCloneCircle() throws CloneNotSupportedException {
        BigDecimal radius = BigDecimal.valueOf(2.2124142);
        Figure circle = new Circle(radius);
        assertTrue(() -> circle instanceof Cloneable,
                "When using clone the class must implement Cloneable");

        Figure cloned = circle.clone();
        assertNotSame(circle, cloned, "Cloned instance is different from the original instance");
        assertEquals(circle.toString(), cloned.toString(),
                "Strings of original and cloned must be same because toString is deterministic!");
    }

}
