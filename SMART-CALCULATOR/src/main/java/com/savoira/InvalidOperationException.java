package com.savoira;

/**
 * Custom runtime exception thrown when an unrecognized operator is encountered.
 */
public class InvalidOperationException extends RuntimeException {
    /**
     * Constructs a new InvalidOperationException with the specified message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidOperationException(String message) {
        super(message);
    }
}
