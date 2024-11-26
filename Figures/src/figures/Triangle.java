package figures;

import java.math.BigDecimal;

public class Triangle extends Figure {
    public Triangle(BigDecimal side1, BigDecimal side2, BigDecimal side3) {
        super(side1, side2, side3);
    }

    @Override
    public Triangle clone() throws CloneNotSupportedException {
        return (Triangle) super.clone();
    }
}
