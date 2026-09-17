package com.savoira;

/**
 * Interface representing an entity whose calculation result can be evaluated.
 * Core contract for polymorphically invoking math operations.
 */
public interface Calculable {
    /**
     * Calculates the numeric result of the operation.
     *
     * @return the result of the calculation as a double
     */
    double calculate();
}
