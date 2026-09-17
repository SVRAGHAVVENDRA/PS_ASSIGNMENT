package com.savoira;

/**
 * Concrete class representing a multiplication operation.
 * Inherits from {@link Operation} and implements {@link Calculable}.
 */
public class Multiplication extends Operation implements Calculable {

    /**
     * Constructs a new Multiplication operation with the specified operands.
     *
     * @param a the first numeric operand
     * @param b the second numeric operand
     */
    public Multiplication(double a, double b) {
        super(a, b);
    }

    /**
     * Calculates the product of the two operands.
     *
     * @return the product of a and b
     */
    @Override
    public double calculate() {
        return a * b;
    }

    /**
     * Returns a string representation of the multiplication operation.
     *
     * @return a string in the format "Multiplication: a * b = result"
     */
    @Override
    public String toString() {
        return String.format("Multiplication: %.1f * %.1f = %.1f", a, b, calculate());
    }
}
