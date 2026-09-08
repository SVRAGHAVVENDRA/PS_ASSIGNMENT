package com.savoira;

/**
 * Utility class for bank loan calculations, conforming to clean-code standards.
 *
 * <p>
 * <b>Original obfuscated method review:</b>
 * <pre>
 * public double calc(double a, double b, int c) {
 *     double r = 1;
 *     for(int i=0;i<c;i++){r=r*(1+b/1200);}
 *     return a*r;
 * }
 * </pre>
 *
 * <b>Clean Code Violations Found:</b>
 * <ol>
 *   <li><b>Meaningless Identifiers:</b> The method name 'calc' and parameters 'a', 'b', 'c', and variable 'r' are cryptically short and non-descriptive. They have been renamed to 'calculateMonthlyCompoundInterest', 'principal', 'annualInterestRate', 'months', and 'interestMultiplier' respectively to reflect their business domain meaning.</li>
 *   <li><b>Magic Numbers:</b> The literal '1200' is a magic number. It has been replaced with descriptive, named constants (MONTHS_IN_YEAR * PERCENTAGE_DIVISOR) to clarify the formula.</li>
 *   <li><b>Formatting and Spacing:</b> The original loop and arithmetic expressions lacked standard whitespace (e.g. 'i<c', 'i++', 'r=r*', 'b/1200'), making the code dense and hard to read. Proper spacing has been restored.</li>
 *   <li><b>Lack of Documentation:</b> The original method had no Javadoc or comments explaining inputs, outputs, units of measure (e.g., that 'b' is annual percentage rate, and interest is compounded monthly), or runtime constraints. Complete Javadoc documentation has been added.</li>
 *   <li><b>Lack of Input Validation:</b> The original code allowed invalid business values (e.g., negative principal, negative rate, or negative time periods) which would result in nonsensical math. Validations have been added to throw IllegalArgumentException in these cases.</li>
 * </ol>
 * </p>
 */
public final class LoanUtils {

    // Prevent instantiation of utility class
    private LoanUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    /**
     * Calculates the future value of a principal sum compounded monthly at a given annual interest rate.
     *
     * @param principal          the initial principal amount (must be non-negative)
     * @param annualInterestRate the annual interest rate as a percentage (e.g., 5.5 for 5.5%; must be non-negative)
     * @param months             the compounding duration in months (must be non-negative)
     * @return the total accumulated amount (principal + compound interest)
     * @throws IllegalArgumentException if principal, interest rate, or duration is negative
     */
    public static double calculateMonthlyCompoundInterest(
            final double principal,
            final double annualInterestRate,
            final int months) {
        
        if (principal < 0.0) {
            throw new IllegalArgumentException("Principal amount cannot be negative.");
        }
        if (annualInterestRate < 0.0) {
            throw new IllegalArgumentException("Annual interest rate cannot be negative.");
        }
        if (months < 0) {
            throw new IllegalArgumentException("Compounding period in months cannot be negative.");
        }

        final int MONTHS_IN_YEAR = 12;
        final int PERCENTAGE_DIVISOR = 100;
        
        // Convert annual percentage rate to monthly decimal rate factor
        final double monthlyRateFactor = annualInterestRate / (MONTHS_IN_YEAR * PERCENTAGE_DIVISOR);

        double interestMultiplier = 1.0;
        for (int i = 0; i < months; i++) {
            interestMultiplier = interestMultiplier * (1.0 + monthlyRateFactor);
        }

        return principal * interestMultiplier;
    }
}
