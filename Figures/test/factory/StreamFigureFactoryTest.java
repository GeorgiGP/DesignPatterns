package factory;

import exceptions.FailedFigureCreationException;
import figures.Circle;
import figures.Figure;
import figures.Triangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StreamFigureFactoryTest {
    private static final File folder = new File("test/examples");

    @BeforeAll
    static void setUp() {
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    @Test
    void testCreateStreamFigureValid() throws IOException {
        BigDecimal bigDecimal = new BigDecimal("3.1352123124412123322312121312");
        BigDecimal bigDecimal2 = new BigDecimal("5.123");
        BigDecimal bigDecimal3 = new BigDecimal("6.223");
        BigDecimal radius = new BigDecimal("98.12");
        Figure triangle = new Triangle(bigDecimal, bigDecimal2, bigDecimal3);
        Figure circle = new Circle(radius);

        File file = new File(folder, "output.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(triangle + System.lineSeparator());
            writer.write(circle + System.lineSeparator());
            writer.flush();
            Reader r = new FileReader(file);
            FigureFactory factory = new StreamFigureFactory(r);
            assertEquals(factory.create().toString(), triangle.toString());
            assertEquals(factory.create().toString(), circle.toString());
        }
    }

    @Test
    void testCreateStreamFigureEmpty() throws IOException {
        File file = new File(folder, "empty.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.close();
            Reader r = new FileReader(file);
            FigureFactory factory = new StreamFigureFactory(r);
            assertThrows(FailedFigureCreationException.class, factory::create,
                    "Fail to read from empty file");
        }
    }

    @Test
    void testReadMultipleTimesIfInvalid() throws IOException {
        File file = new File(folder, "invalid.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("rectangle 3.2   ");
            writer.close();
            Reader r = new FileReader(file);
            FigureFactory factory = new StreamFigureFactory(r);
            assertThrows(FailedFigureCreationException.class, factory::create,
                    "Fail to read from invalid file");
            assertThrows(FailedFigureCreationException.class, factory::create,
                    "Fail to read from invalid file twice");
            assertThrows(FailedFigureCreationException.class, factory::create,
                    "Fail to read from empty file third time");
        }
    }

    @Test
    void testCreateStreamFigureWrongSyntax() throws IOException{
        File file = new File(folder, "wrong.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("triangle 3 2");
            writer.close();
            Reader r = new FileReader(file);
            FigureFactory factory = new StreamFigureFactory(r);
            assertThrows(FailedFigureCreationException.class, factory::create,
                    "Fail to read from empty file");
        }
    }

    @Test
    void testStreamFigureFactoryNull() {
        assertThrows(IllegalArgumentException.class, () -> new StreamFigureFactory(null),
                "Can't put null in StreamFigureFactory");
    }
}
