package com.savoira;

/**
 * Abstract class representing a mathematical operation.
 * Implements {@link Calculable} to define standard calculation behavior.
 */
public abstract class Operation implements Calculable {
    /** The first numeric operand. */
    protected final double a;
    /** The second numeric operand. */
    protected final double b;

    /**
     * Constructs a new Operation with two operands.
     *
     * @param a the first numeric operand
     * @param b the second numeric operand
     */
    protected Operation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Abstract method to calculate the result of the operation.
     *
     * @return the double result of the operation
     */
    @Override
    public abstract double calculate();
}
