package com.figures;

import java.math.BigDecimal;
import java.util.Collections;

public class Polygon extends Figure {
    public Polygon(BigDecimal... values) {
        super(values);
    }

    @Override
    protected void validateFigureExists() {
        calculatePerimeter();
        validateOverflow(calculatedPerimeter);

        BigDecimal multiplyBy = BigDecimal.valueOf(2);
        BigDecimal biggestSide = Collections.max(sides);
        if (calculatedPerimeter.compareTo(biggestSide.multiply(multiplyBy)) <= 0) {
            throw new IllegalArgumentException(
                    "The figure is not valid, one of the sides is bigger than the sum of the rest of them!");
        }
    }

    @Override
    public final BigDecimal calculatePerimeter() {
        if (calculatedPerimeter != null) {
            return calculatedPerimeter;
        }
        calculatedPerimeter = BigDecimal.ZERO;
        for (BigDecimal value : sides) {
            calculatedPerimeter = calculatedPerimeter.add(value);
        }
        return calculatedPerimeter;
    }
}
