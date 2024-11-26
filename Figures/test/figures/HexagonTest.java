package figures;

import java.math.BigDecimal;

public class HexagonTest extends Figure {
    public HexagonTest(BigDecimal side1, BigDecimal side2, BigDecimal side3,
                   BigDecimal side4, BigDecimal side5, BigDecimal side6) {
        super(side1, side2, side3, side4, side5, side6);
    }

    @Override
    public HexagonTest clone() throws CloneNotSupportedException {
        return (HexagonTest) super.clone();
    }
}
