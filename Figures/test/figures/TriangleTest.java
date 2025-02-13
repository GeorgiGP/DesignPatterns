package figures;

import com.figures.Figure;
import com.figures.Triangle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {
    private static final double EPSION = 1e-15;

    private BigDecimal calculatePerimeterTesting(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) {
        return sideA.add(sideB).add(sideC);
    }


    @Test
    void testInvalidTriangle() {
        BigDecimal sideA = BigDecimal.valueOf(3);
        BigDecimal sideB = BigDecimal.valueOf(6);
        BigDecimal sideC = BigDecimal.valueOf(9);
        assertThrows(IllegalArgumentException.class, () -> new Triangle(sideA, sideB, sideC),
                "Triangle 3 6 9 is not valid");
        }

    @Test
    void testCalculatePerimeterIntegers() {
        BigDecimal sideA = BigDecimal.valueOf(30);
        BigDecimal sideB = BigDecimal.valueOf(98);
        BigDecimal sideC = BigDecimal.valueOf(70);
        Figure triangle = new Triangle(sideA, sideB, sideC);

        BigDecimal perimeter = triangle.calculatePerimeter();
        BigDecimal expectedPerimeter = calculatePerimeterTesting(sideA, sideB, sideC);

        assertEquals(perimeter, expectedPerimeter, "Formula should be A + B + C when all sides are valid!");
    }

    @Test
    void testCalculatePerimeterDoubles() {
        BigDecimal sideA = BigDecimal.valueOf(6.5121812841124);
        BigDecimal sideB = BigDecimal.valueOf(8.51215134124);
        BigDecimal sideC = BigDecimal.valueOf(2.9124817247412);
        Figure triangle = new Triangle(sideA, sideB, sideC);

        BigDecimal perimeter = triangle.calculatePerimeter();
        BigDecimal expectedPerimeter = calculatePerimeterTesting(sideA, sideB, sideC);

        assertEquals(perimeter, expectedPerimeter,
                "Formula should be A + B + C and takes numbers with floating point");
    }

    @Test
    void testCalculatePerimeterDoublesCloseToZero() {
        BigDecimal sideA = BigDecimal.valueOf(EPSION).multiply(BigDecimal.valueOf(2));
        BigDecimal sideB = BigDecimal.valueOf(EPSION).multiply(BigDecimal.valueOf(3));
        BigDecimal sideC = BigDecimal.valueOf(EPSION).multiply(BigDecimal.valueOf(4));

        Figure triangle = new Triangle(sideA, sideB, sideC);
        BigDecimal perimeter = triangle.calculatePerimeter();
        BigDecimal expectedPerimeter = calculatePerimeterTesting(sideA, sideB, sideC);

        assertEquals(perimeter, expectedPerimeter, "Perimeter should be calculated if all numbers are above 0");
    }

    @Test
    void testOverflowPerimeterAboveDoubleMax() {
        BigDecimal sideA = BigDecimal.valueOf(Double.MAX_VALUE);
        BigDecimal sideB = BigDecimal.valueOf(Double.MAX_VALUE)
                .divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP);
        BigDecimal sideC = BigDecimal.valueOf(Double.MAX_VALUE)
                .divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_UP);

        assertThrows(ArithmeticException.class , () -> new Triangle(sideA, sideB, sideC),
                "Perimeter should overflow if above double max!");
    }

    @Test
    void testCalculatePerimeterSymetric() {
        BigDecimal sideA = BigDecimal.valueOf(5.1251514121);
        BigDecimal sideB = BigDecimal.valueOf(9.1249194828412);
        BigDecimal sideC = BigDecimal.valueOf(8.123124);
        Figure triangle = new Triangle(sideA, sideB, sideC);
        Figure triangle2 = new Triangle(sideA, sideC, sideB);

        BigDecimal perimeter = triangle.calculatePerimeter();
        BigDecimal perimeter2 = triangle2.calculatePerimeter();

        assertEquals(perimeter, perimeter2, "Perimeter should be the same for triangles ABC and ACB");
    }

    @Test
    void testOverflowPerimeterCloseToUnderMaxDouble() {
        BigDecimal sideAThirdMinus2 = BigDecimal.valueOf(Double.MAX_VALUE)
                .divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_UP)
                .subtract(BigDecimal.valueOf(2));

        BigDecimal sideBThirdPlus1 = sideAThirdMinus2.add(BigDecimal.valueOf(1));
        BigDecimal sideCThird = sideAThirdMinus2.add(BigDecimal.valueOf(2));

        assertDoesNotThrow(() -> new Triangle(sideAThirdMinus2, sideBThirdPlus1, sideCThird),
                "If the perimeter is close to Double.MaxValue, should not throw ArithmeticException!");
    }

    @Test
    void testNegativeSide() {
        BigDecimal sideA = BigDecimal.valueOf(5);
        BigDecimal sideB = BigDecimal.valueOf(-1);
        BigDecimal sideC = BigDecimal.valueOf(7);
        assertThrows(IllegalArgumentException.class , () -> new Triangle(sideA, sideB, sideC),
                "No negative side!");
    }

    @Test
    void testZeroSide() {
        BigDecimal sideA = BigDecimal.valueOf(0);
        BigDecimal sideB = BigDecimal.valueOf(3);
        BigDecimal sideC = BigDecimal.valueOf(2);
        assertThrows(IllegalArgumentException.class , () -> new Triangle(sideA, sideB, sideC),
                "Side should not be zero!");
    }

    @Test
    void testToStringTriangle() {
        BigDecimal sideA = BigDecimal.valueOf(2.2124142);
        BigDecimal sideB = BigDecimal.valueOf(23.12);
        BigDecimal sideC = BigDecimal.valueOf(24.00012);
        Figure triangle = new Triangle(sideA, sideB, sideC);
        assertEquals("triangle 2.2124142 23.12 24.00012", triangle.toString(),
                "Name of the class in string must be with lower case, name and sides separated with \" \" and sides should be saved with exact value!");
    }

    @Test
    void testCloneTriangle() throws CloneNotSupportedException {
        BigDecimal sideA = BigDecimal.valueOf(8127.213);
        BigDecimal sideB = BigDecimal.valueOf(12311);
        BigDecimal sideC = BigDecimal.valueOf(10000.1241115151);

        Figure triangle = new Triangle(sideA, sideB, sideC);
        assertTrue(() -> triangle instanceof Cloneable,
                "When using clone the class must implement Cloneable");

        Figure cloned = triangle.clone();
        assertNotSame(triangle, cloned, "Cloned instance is different from the original instance");
        assertEquals(triangle.toString(), cloned.toString(),
                "Strings of original and cloned must be same because toString is deterministic!");
    }

}
