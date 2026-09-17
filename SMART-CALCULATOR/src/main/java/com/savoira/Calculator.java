package com.savoira;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles the calculation logic for arithmetic operations and provides mathematical utilities.
 * Follows the WORM (Write Once, Reuse Many) principle by centralizing arithmetic execution.
 */
public class Calculator {

    private static final Logger logger = LogManager.getLogger(Calculator.class);

    /**
     * Performs calculations for the given Operation.
     * Delegates execution to the polymorphic calculate implementation.
     *
     * @param operation the Operation to run
     * @return the double result of the operation
     * @throws InvalidOperationException if operation is null
     */
    public double calculate(Operation operation) {
        if (operation == null) {
            throw new InvalidOperationException("Unknown operator");
        }
        logger.debug("Executing calculation: {}", operation);
        return operation.calculate();
    }

    /**
     * Calculates the square root of a given number.
     *
     * @param number the double value to find the square root of
     * @return the square root of the number, or Double.NaN if the number is negative
     */
    public static double squareRoot(double number) {
        if (number < 0) {
            logger.debug("Square root of a negative number attempted: {}", number);
            logger.warn("Error: Cannot calculate square root of a negative number.");
            return Double.NaN;
        }
        logger.debug("Calculating square root of: {}", number);
        return Math.sqrt(number);
    }

    /**
     * Calculates the percentage representation of a given number (divides by 100).
     *
     * @param number the double value to convert to a percentage
     * @return the value divided by 100
     */
    public static double percentage(double number) {
        logger.debug("Calculating percentage of: {}", number);
        return number / 100.0;
    }

    /**
     * Validates whether the given operator string is a supported arithmetic operator.
     * Supported operators are: {@code +}, {@code -}, {@code *}, {@code /}, {@code %}, {@code sqrt}.
     *
     * @param op the operator string to validate
     * @return {@code true} if op is one of +, -, *, /, %, sqrt; {@code false} otherwise
     */
    public static boolean isValidOperator(String op) {
        if (op == null) {
            return false;
        }
        return switch (op.toLowerCase()) {
            case "+", "-", "*", "/", "%", "sqrt" -> true;
            default -> false;
        };
    }
}
