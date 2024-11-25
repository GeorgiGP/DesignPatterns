package exceptions;

public class FailedFigureCreationException extends RuntimeException {
    public FailedFigureCreationException(String message) {
        super(message);
    }

    public FailedFigureCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
