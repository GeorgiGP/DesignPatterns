package factory;

import com.factory.FigureFactory;
import com.factory.RandomFigureFactory;
import com.figures.Circle;
import com.figures.Figure;
import com.figures.Rectangle;
import com.figures.Triangle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RandomFigureFactoryTest {
    List<Figure> allFiguresRightDistributionAttributesBiggerThan(int countRandomFigures, BigDecimal tooSmall, int leastOccurClasses) {
        List<Figure> figures = new ArrayList<>();
        FigureFactory factory = new RandomFigureFactory();
        Map<Class<?>, Integer> listOfClasses = new HashMap<>();
        listOfClasses.put(Triangle.class, 0);
        listOfClasses.put(Rectangle.class, 0);
        listOfClasses.put(Circle.class, 0);

        for (int i = 0; i < countRandomFigures; i++) {
            Figure figure = factory.create();
            List<BigDecimal> sides = figure.getSides();
            for (BigDecimal side : sides) {
                if (side.compareTo(tooSmall) < 0) {
                    throw new IllegalArgumentException("Very low chance to contain element" + tooSmall + ". " +
                            "Try again with this test to see if it appears again.");
                }
            }
            listOfClasses.put(figure.getClass(), listOfClasses.getOrDefault(figure.getClass(), 0) + 1);
            figures.add(figure);
        }

        for (Class<?> clazz : listOfClasses.keySet()) {
            if (listOfClasses.get(clazz) < leastOccurClasses) {
                throw new IllegalArgumentException("distribution of the figures should be more equal!" +
                        "Try again with this test to see if it appears again.");
            }
        }
        return figures;
    }

    @Test
    void testCreateRandomFigure() {
        int size = 1000;
        BigDecimal tooSmall = BigDecimal.valueOf(0.1);
        assertDoesNotThrow(() -> allFiguresRightDistributionAttributesBiggerThan(size, tooSmall, 5));
    }
}
