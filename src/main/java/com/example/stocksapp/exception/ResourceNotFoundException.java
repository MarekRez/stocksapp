package com.example.stocksapp.exception;

public class ResourceNotFoundException extends RuntimeException {

    // Constructor that accepts a message
    public ResourceNotFoundException(String message) {
        super(message); // Pass the message to the superclass (RuntimeException)
    }
}
