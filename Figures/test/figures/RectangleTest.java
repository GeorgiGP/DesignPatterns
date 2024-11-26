package figures;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {
    private static final double EPSION = 1e-15;

    private BigDecimal calculatePerimeterTesting(BigDecimal sideA, BigDecimal sideB) {
        return BigDecimal.valueOf(2).multiply(sideA.add(sideB));
    }

    @Test
    void testCalculatePerimeterIntegers() {
        BigDecimal sideA = BigDecimal.valueOf(3);
        BigDecimal sideB = BigDecimal.valueOf(98);
        Figure rectangle = new Rectangle(sideA, sideB);

        BigDecimal perimeter = rectangle.calculatePerimeter();
        BigDecimal expectedPerimeter = calculatePerimeterTesting(sideA, sideB);

        assertEquals(perimeter, expectedPerimeter, "Formula should be 2*(A + B)");
    }

    @Test
    void testCalculatePerimeterDoubles() {
        BigDecimal sideA = BigDecimal.valueOf(3.21313);
        BigDecimal sideB = BigDecimal.valueOf(8.51215134124);
        Figure rectangle = new Rectangle(sideA, sideB);

        BigDecimal perimeter = rectangle.calculatePerimeter();
        BigDecimal expectedPerimeter = calculatePerimeterTesting(sideA, sideB);

        assertEquals(perimeter, expectedPerimeter, "Formula should be 2*(A + B)");
    }

    @Test
    void testCalculatePerimeterDoublesCloseToZero() {
        BigDecimal sideA = BigDecimal.valueOf(EPSION);
        BigDecimal sideB = sideA.pow(2).multiply(BigDecimal.valueOf(3));
        Figure rectangle = new Rectangle(sideA, sideB);

        BigDecimal perimeter = rectangle.calculatePerimeter();
        BigDecimal expectedPerimeter = calculatePerimeterTesting(sideA, sideB);

        assertEquals(perimeter, expectedPerimeter, "Formula should be 2*(A + B)");
    }

    @Test
    void testOverflowDoubleMaxSideFirst() {
        BigDecimal sideA = BigDecimal.valueOf(Double.MAX_VALUE);
        BigDecimal sideB = BigDecimal.valueOf(1);
        assertThrows(ArithmeticException.class , () -> new Rectangle(sideA, sideB),
                "Perimeter should overflow if first side is Max double!");
    }

    @Test
    void testOverflowDoubleMaxSideSecond() {
        BigDecimal sideA = BigDecimal.valueOf(1);
        BigDecimal sideB = BigDecimal.valueOf(Double.MAX_VALUE);
        assertThrows(ArithmeticException.class , () -> new Rectangle(sideA, sideB),
                "Perimeter should overflow if second side is Max double!");
    }

    @Test
    void testCalculatePerimeterSymetric() {
        BigDecimal sideA = BigDecimal.valueOf(5.1251514121);
        BigDecimal sideB = BigDecimal.valueOf(9.1249194828412);
        Figure rectangle = new Rectangle(sideA, sideB);
        Figure rectangle2 = new Rectangle(sideB, sideA);

        BigDecimal perimeter = rectangle.calculatePerimeter();
        BigDecimal perimeter2 = rectangle2.calculatePerimeter();

        assertEquals(perimeter, perimeter2, "Formula should be 2*(A + B) even when working with doubles close to Zero");
    }

    @Test
    void testOverflowPerimeterCloseToUnderMaxDouble() {
        BigDecimal sideAHalf = BigDecimal.valueOf(Double.MAX_VALUE)
                .divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP)
                .subtract(BigDecimal.valueOf(2));

        BigDecimal sideB = BigDecimal.valueOf(1);

        assertDoesNotThrow(() -> new Rectangle(sideAHalf, sideB),
                "If the perimeter is close to Double.MaxValue, should not throw ArithmeticException!");
    }

    @Test
    void testOverflowPerimeterCloseToOverMaxDouble() {
        BigDecimal sideAHalf = BigDecimal.valueOf(Double.MAX_VALUE)
                .divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP)
                .subtract(BigDecimal.valueOf(2));

        BigDecimal sideB = BigDecimal.valueOf(3);

        assertThrows(ArithmeticException.class, () -> new Rectangle(sideAHalf, sideB),
                "If the perimeter is close to Double.MaxValue, should not throw ArithmeticException!");
    }

    @Test
    void testNegativeSide() {
        BigDecimal sideA = BigDecimal.valueOf(5);
        BigDecimal sideB = BigDecimal.valueOf(-1);
        assertThrows(IllegalArgumentException.class , () -> new Rectangle(sideA, sideB),
                "No negative side!");
    }

    @Test
    void testZeroSide() {
        BigDecimal sideA = BigDecimal.valueOf(0);
        BigDecimal sideB = BigDecimal.valueOf(3);
        assertThrows(IllegalArgumentException.class , () -> new Rectangle(sideA, sideB),
                "Side should not be zero!");
    }

    @Test
    void testToStringRectangle() {
        BigDecimal sideA = BigDecimal.valueOf(2.2124142);
        BigDecimal sideB = BigDecimal.valueOf(Double.MAX_VALUE).divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_UP);
        Figure rectangle = new Rectangle(sideA, sideB);
        assertEquals("rectangle 2.2124142 " + sideB, rectangle.toString(),
                "Name of the class in string must be with lower case, name and sides separated with \" \" and side "
                        + sideB + "should be saved with exact value!");
    }

    @Test
    void testCloneRectangle() throws CloneNotSupportedException {
        BigDecimal sideA = BigDecimal.valueOf(8127.213);
        BigDecimal sideB = BigDecimal.valueOf(Double.MAX_VALUE).divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_UP);

        Figure rectangle = new Rectangle(sideA, sideB);
        assertTrue(() -> rectangle instanceof Cloneable,
                "When using clone the class must implement Cloneable");

        Figure cloned = rectangle.clone();
        assertNotSame(rectangle, cloned, "Cloned instance is different from the original instance");
        assertEquals(rectangle.toString(), cloned.toString(),
                "Strings of original and cloned must be same because toString is deterministic!");
    }
}
