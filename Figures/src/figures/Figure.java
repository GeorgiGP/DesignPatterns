package figures;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Figure implements Cloneable {
    protected final List<BigDecimal> sides; //sides in order
    protected BigDecimal calculatedPerimeter = null;

    /**
     * @throws IllegalArgumentException if one of the sides lengths is not a positive number
     * @throws IllegalArgumentException if one of the sides is bigger than the sum of the rest of them
     * @throws ArithmeticException if the sum of sides overflows
     *
     * @param values side values
     */
    public Figure(BigDecimal... values) {
        sides = new ArrayList<>();
        for (BigDecimal value : values) {
            validateSideLen(value);
            sides.add(value);
        }
        validateFigureExists();
    }

    /**
     * @throws IllegalArgumentException if one of the sides is bigger than the sum of the rest of them
     * @throws ArithmeticException if the sum of sides overflows
     */
    protected final void validateFigureExists() {
        calculatePerimeter();
        validateOverflow(calculatedPerimeter);

        BigDecimal multiplyBy = BigDecimal.valueOf(2);
        BigDecimal biggestSide = Collections.max(sides);
        if (calculatedPerimeter.compareTo(biggestSide.multiply(multiplyBy)) <= 0) {
            throw new IllegalArgumentException(
                    "The figure is not valid, one of the sides is bigger than the sum of the rest of them!");
        }
    }

    /**
     * @throws IllegalArgumentException if one of the sides lengths is not a positive number
     */
    private void validateSideLen(BigDecimal sideLen) {
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal doubleMax = BigDecimal.valueOf(Double.MAX_VALUE);
        if ((sideLen.compareTo(zero) <= 0) || sideLen.compareTo(doubleMax) > 0) {
            throw new IllegalArgumentException("Invalid len value, must be between EPSILON and Double.MAX_VALUE");
        }
    }

    /**
     * @throws ArithmeticException if one a len overflows
     */
    private void validateOverflow(BigDecimal len) {
        BigDecimal overflow = BigDecimal.valueOf(Double.MAX_VALUE);
        if (len.compareTo(overflow) >= 0) {
            throw new ArithmeticException("While doing some arithmetic operations, type overflow has occurred!");
        }
    }

    /**
     * @throws ArithmeticException if the sum of sides overflows
     */
    public BigDecimal calculatePerimeter() {
        if (calculatedPerimeter != null) {
            return calculatedPerimeter;
        }
        calculatedPerimeter = BigDecimal.ZERO;
        for (BigDecimal value : sides) {
            calculatedPerimeter = calculatedPerimeter.add(value);
        }
        return calculatedPerimeter;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        StringBuilder str = new StringBuilder(className);
        char upperSymbol = str.charAt(0);
        upperSymbol = Character.toLowerCase(upperSymbol);
        str.setCharAt(0, upperSymbol);

        for (BigDecimal value : sides) {
            str.append(" ").append(value);
        }
        return str.toString();
    }

    @Override
    public Figure clone() throws CloneNotSupportedException {
        return (Figure) super.clone();
    }

    public List<BigDecimal> getSides() {
        return Collections.unmodifiableList(sides);
    }
}

