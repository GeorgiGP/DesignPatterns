package figures;

import exceptions.InvalidCommand;
import factory.AbstractFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FigureCollection {
    private final List<Figure> figures;

    public FigureCollection() {
        figures = new ArrayList<>();
    }

    public void add(Figure figure) {
        figures.add(figure);
    }

    public void display() {
        for (Figure figure : figures) {
            System.out.println(figure);
        }
    }

    public void delete(int idxDelete) {
        if (idxDelete >= 0 && idxDelete < figures.size()) {
            figures.remove(idxDelete);
        } else {
            throw new InvalidCommand("Index out of bounds, must be between 0 and " + (figures.size() - 1));
        }
    }

    public void copy(int idxCopy) throws CloneNotSupportedException {
        if (idxCopy >= 0 && idxCopy < figures.size()) {
            figures.add(figures.get(idxCopy).clone());
        } else {
            throw new InvalidCommand("Index out of bounds, must be between 0 and " + (figures.size() - 1));
        }
    }

    public void toFile(String input) throws IOException {
        File outputFile = new File(AbstractFactory.getExamplesFolder(), input);
        try (Writer writer = new FileWriter(outputFile)) {
            writer.write(figures.size() + System.lineSeparator());
            for (Figure figure : figures) {
                writer.write(figure.toString() + System.lineSeparator());
            }
        }
    }

    public BigDecimal perimeter(int idxCopy)  {
        if (idxCopy >= 0 && idxCopy < figures.size()) {
            return figures.get(idxCopy).calculatePerimeter();
        } else {
            throw new InvalidCommand("Index out of bounds, must be between 0 and " + (figures.size() - 1));
        }
    }

    public Figure get(int idx) throws CloneNotSupportedException {
        if (idx >= 0 && idx < figures.size()) {
            return figures.get(idx).clone();
        } else {
            return null;
        }
    }
}
