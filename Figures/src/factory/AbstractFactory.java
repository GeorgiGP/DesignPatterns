package factory;

import figures.FigureCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class AbstractFactory {
    private final FigureFactory figureFactory;
    private FigureCollection figureCollection = null;
    private static final File CREATING_EXAMPLES_FOLDER = new File("src/examples");

    public AbstractFactory(String typeFactory) throws FileNotFoundException {
        Scanner scanner = new Scanner(typeFactory);
        if (!scanner.hasNext()) {
            scanner.close();
            throw new IllegalArgumentException("Source required for reading figures");
        }
        String nextWord = scanner.next();
        nextWord = nextWord.strip().toLowerCase();
        switch (nextWord) {
            case "random":
                figureFactory = new RandomFigureFactory();
                break;
            case "systemin":
                Reader reader = new InputStreamReader(System.in);
                figureFactory = new StreamFigureFactory(reader);
                break;

            case "file":
                if (!scanner.hasNext()) {
                    throw new IllegalArgumentException("<filename> required for reading figures from file");
                }
                File file = new File(getExamplesFolder(), scanner.next());
                Reader fileReader = new FileReader(file);
                figureFactory = new StreamFigureFactory(fileReader);
                break;
            default:
                scanner.close();
                throw new IllegalArgumentException("<" + nextWord + "> was required for choosing option of reading.");
        }
        scanner.close();
    }

    public static File getExamplesFolder() {
        if (!CREATING_EXAMPLES_FOLDER.exists()) {
            CREATING_EXAMPLES_FOLDER.mkdir();
        }
        return CREATING_EXAMPLES_FOLDER;
    }

    public FigureCollection collection() {
        if (figureCollection != null) {
            return figureCollection;
        }
        figureCollection = new FigureCollection();
        int count = figureFactory.countFigures();
        for (int i = 0; i < count; ++i) {
            figureCollection.add(figureFactory.create());
        }
        return figureCollection;
    }
}
