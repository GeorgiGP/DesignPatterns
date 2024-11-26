package figures;

import java.math.BigDecimal;

public class Rectangle extends Figure {
    public Rectangle(BigDecimal sideA, BigDecimal sideB) {
        super(sideA, sideB);
    }

    @Override
    public BigDecimal calculatePerimeter() {
        if (calculatedPerimeter != null) {
            return calculatedPerimeter;
        }
        BigDecimal multiplyByTwo = BigDecimal.valueOf(2);
        return calculatedPerimeter = multiplyByTwo.multiply(sides.get(0).add(sides.get(1)));
    }

    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        return (Rectangle) super.clone();
    }
}
