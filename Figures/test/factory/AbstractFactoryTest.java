package factory;

import figures.Circle;
import figures.Figure;
import figures.FigureCollection;
import figures.Triangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractFactoryTest {
    private static final File folder = new File("src/examples");

    @BeforeAll
    static void setUp() {
        if (!folder.exists()) {
            folder.mkdir();
        }
    }


    @Test
    void testAbstractFactory() throws IOException, CloneNotSupportedException {
        BigDecimal bigDecimal = new BigDecimal("3.1352123124412123322312121312");
        BigDecimal bigDecimal2 = new BigDecimal("5.123");
        BigDecimal bigDecimal3 = new BigDecimal("6.223");
        BigDecimal radius = new BigDecimal("98.12");
        Figure triangle = new Triangle(bigDecimal, bigDecimal2, bigDecimal3);
        Figure circle = new Circle(radius);
        FigureCollection collection = new FigureCollection();
        collection.add(triangle);
        collection.add(circle);
        File file = new File(folder, "outputAbstract.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(2 + System.lineSeparator());
            writer.write(triangle + System.lineSeparator());
            writer.write(circle + System.lineSeparator());
        }

        AbstractFactory factory = new AbstractFactory("file outputAbstract.txt");
        FigureCollection collection2 = factory.collection();
        assertEquals(collection.get(0).toString(), collection2.get(0).toString());
        assertEquals(collection.get(1).toString(), collection2.get(1).toString());
    }
}
