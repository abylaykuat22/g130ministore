package com.example.g130ministore.exception;

public class IncorrectRequestException extends RuntimeException {

    public IncorrectRequestException() {
        super();
    }

    public IncorrectRequestException(String message) {
        super(message);
    }
}
