package com.savoira;

/**
 * Concrete class representing a subtraction operation.
 * Inherits from {@link Operation} and implements {@link Calculable}.
 */
public class Subtraction extends Operation implements Calculable {

    /**
     * Constructs a new Subtraction operation with the specified operands.
     *
     * @param a the first numeric operand
     * @param b the second numeric operand
     */
    public Subtraction(double a, double b) {
        super(a, b);
    }

    /**
     * Calculates the difference of the two operands.
     *
     * @return the result of a - b
     */
    @Override
    public double calculate() {
        return a - b;
    }

    /**
     * Returns a string representation of the subtraction operation.
     *
     * @return a string in the format "Subtraction: a - b = result"
     */
    @Override
    public String toString() {
        return String.format("Subtraction: %.1f - %.1f = %.1f", a, b, calculate());
    }
}
