package com.savoira;

/**
 * Custom exception representing a division by zero error.
 * Extends {@link ArithmeticException} to conform to standard Java math exception hierarchies.
 */
public class DivisionByZeroException extends ArithmeticException {
    /**
     * Constructs a new DivisionByZeroException with a default error message.
     */
    public DivisionByZeroException() {
        super("Cannot divide by zero");
    }
}
