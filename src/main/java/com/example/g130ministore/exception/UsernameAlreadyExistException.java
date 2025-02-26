package com.example.g130ministore.exception;

public class UsernameAlreadyExistException extends RuntimeException {

    public UsernameAlreadyExistException() {
        super();
    }

    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
