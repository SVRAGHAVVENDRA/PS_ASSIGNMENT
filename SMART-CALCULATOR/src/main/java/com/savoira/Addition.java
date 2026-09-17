package com.savoira;

/**
 * Concrete class representing an addition operation.
 * Inherits from {@link Operation} and implements {@link Calculable}.
 */
public class Addition extends Operation implements Calculable {

    /**
     * Constructs a new Addition operation with the specified operands.
     *
     * @param a the first numeric operand
     * @param b the second numeric operand
     */
    public Addition(double a, double b) {
        super(a, b);
    }

    /**
     * Calculates the sum of the two operands.
     *
     * @return the sum of a and b
     */
    @Override
    public double calculate() {
        return a + b;
    }

    /**
     * Returns a string representation of the addition operation.
     *
     * @return a string in the format "Addition: a + b = result"
     */
    @Override
    public String toString() {
        return String.format("Addition: %.1f + %.1f = %.1f", a, b, calculate());
    }
}
