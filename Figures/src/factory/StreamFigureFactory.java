package factory;

import exceptions.FailedFigureCreationException;
import figures.Figure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class StreamFigureFactory extends FigureFactory {
    private final BufferedReader bufferedReader;

    public StreamFigureFactory(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("reader is null");
        }
        this.bufferedReader = new BufferedReader(reader);
    }

    @Override
    public int countFigures() {
        try {
            String line = bufferedReader.readLine();
            return Integer.parseInt(line);
        } catch (Exception e) {
            throw new FailedFigureCreationException("Invalid stream syntax");
        }
    }

    @Override
    public Figure create() {
        try {
            String line;
            if ((line = bufferedReader.readLine()) != null) {
                return (new StringToFigure()).createFrom(line);
            }
        } catch (IOException e) {
            throw new FailedFigureCreationException(e.getMessage(), e.getCause());
        }
        throw new FailedFigureCreationException("No figure found");
    }
}
