package com.savoira.assessment;

/**
 * Task D1: Math Utilities
 * A static utility class providing functions for simple interest, compound interest, and rounding.
 */
public final class MathUtils {

    // prevent instantiation — utility class
    private MathUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    /**
     * Rounds a double value to exactly two decimal places.
     *
     * @param v the value to round
     * @return the rounded value
     */
    public static double roundToTwoDecimalPlaces(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    /**
     * Calculates the simple interest.
     * Interest = (Principal * Rate * Time) / 100
     *
     * @param p the principal amount
     * @param r the annual interest rate (in percentage, e.g. 5.5 for 5.5%)
     * @param t the duration in years
     * @return the calculated simple interest
     */
    public static double calculateSimpleInterest(double p, double r, double t) {
        if (p < 0.0 || r < 0.0 || t < 0.0) {
            throw new IllegalArgumentException("Principal, rate, and time must be non-negative.");
        }
        return (p * r * t) / 100.0;
    }

    /**
     * Calculates the compound interest earned.
     * Formula: Interest = Principal * [ (1 + (Rate / 100) / n)^(n * t) - 1 ]
     *
     * @param p the principal amount
     * @param r the annual interest rate (in percentage, e.g. 5.5 for 5.5%)
     * @param n the number of times interest is compounded per year
     * @param t the duration in years
     * @return the calculated compound interest earned
     */
    public static double calculateCompoundInterest(double p, double r, int n, double t) {
        if (p < 0.0 || r < 0.0 || n <= 0 || t < 0.0) {
            throw new IllegalArgumentException("Principal, rate, and time must be non-negative. Compounding frequency must be > 0.");
        }
        double amount = p * Math.pow(1.0 + (r / 100.0) / n, n * t);
        return amount - p;
    }
}
