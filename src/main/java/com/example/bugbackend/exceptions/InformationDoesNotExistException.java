package com.example.bugbackend.exceptions;

public class InformationDoesNotExistException extends RuntimeException {
    public InformationDoesNotExistException(String message) {
        super(message);
    }
}
