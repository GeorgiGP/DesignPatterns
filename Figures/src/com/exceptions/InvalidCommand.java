package com.exceptions;

public class InvalidCommand extends RuntimeException {
    public InvalidCommand(String message) {
        super(message);
    }

    public InvalidCommand(String message, Throwable cause) {
        super(message, cause);
    }
}
