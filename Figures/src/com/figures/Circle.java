package com.figures;

import java.math.BigDecimal;

public class Circle extends Figure {

    public Circle(BigDecimal radius) {
        super(radius);
    }

    @Override
    public BigDecimal calculatePerimeter() {
        if (calculatedPerimeter != null) {
            return calculatedPerimeter;
        }
        BigDecimal multiplyByTwo = BigDecimal.valueOf(2);
        return calculatedPerimeter = multiplyByTwo.multiply(BigDecimal.valueOf(Math.PI)).multiply(sides.getFirst());
    }

}
