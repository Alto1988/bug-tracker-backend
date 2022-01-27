package com.example.bugbackend.exceptions;

public class InformationExistsException extends RuntimeException {
    public InformationExistsException(String message) {
        super(message);
    }
}
