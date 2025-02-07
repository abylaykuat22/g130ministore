package com.example.g130ministore.exception;

public class EntityUniqueException extends RuntimeException {

    public EntityUniqueException() {
        super();
    }

    public EntityUniqueException(String message) {
        super(message);
    }
}
