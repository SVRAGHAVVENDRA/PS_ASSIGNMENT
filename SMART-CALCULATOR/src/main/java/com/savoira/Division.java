package com.savoira;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concrete class representing a division operation.
 * Inherits from {@link Operation} and implements {@link Calculable}.
 */
public class Division extends Operation implements Calculable {
    private static final Logger logger = LogManager.getLogger(Division.class);

    /**
     * Constructs a new Division operation with the specified operands.
     *
     * @param a the first numeric operand
     * @param b the second numeric operand
     */
    public Division(double a, double b) {
        super(a, b);
    }

    /**
     * Calculates the quotient of the two operands using {@link BigDecimal} with scale 10.
     *
     * @return the result of a / b as a double
     * @throws DivisionByZeroException if the divisor b is zero
     */
    @Override
    public double calculate() {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        BigDecimal bdA = BigDecimal.valueOf(this.a);
        BigDecimal bdB = BigDecimal.valueOf(this.b);
        return bdA.divide(bdB, 10, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Returns a string representation of the division operation.
     *
     * @return a string in the format "Division: a / b = result"
     */
    @Override
    public String toString() {
        return String.format("Division: %.1f / %.1f = %.1f", a, b, calculate());
    }
}
