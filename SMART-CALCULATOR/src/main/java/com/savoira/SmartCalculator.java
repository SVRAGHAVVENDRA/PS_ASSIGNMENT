package com.savoira;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point for the SmartCalculator interactive CLI application.
 * Coordinates user input scanning, parsing, operational logic delegation,
 * and console results display.
 */
public class SmartCalculator {

    private static final Logger logger = LogManager.getLogger(SmartCalculator.class);

    /**
     * Calculates the result for the given inputs by delegating to the new OOP classes.
     * Maintained for backward compatibility with earlier phase builds and tests.
     *
     * @param num1     the first numeric operand
     * @param num2     the second numeric operand
     * @param operator the operator character (+, -, *, /, %)
     * @return the result of the calculation, or Double.NaN if an error occurs
     * @deprecated Use {@link Calculator#calculate(Operation)} instead.
     */
    @Deprecated
    public static double calculate(double num1, double num2, char operator) {
        Calculator calculator = new Calculator();
        Operation operation = switch (operator) {
            case '+' -> new Addition(num1, num2);
            case '-' -> new Subtraction(num1, num2);
            case '*' -> new Multiplication(num1, num2);
            case '/' -> new Division(num1, num2);
            case '%' -> new Modulo(num1, num2);
            default -> null;
        };
        return calculator.calculate(operation);
    }

    /**
     * Validates whether the given operator string is supported.
     * Delegates to {@link Calculator#isValidOperator(String)}.
     *
     * @param op the operator string to validate
     * @return {@code true} if op is one of +, -, *, /, %; {@code false} otherwise
     */
    public static boolean isValidOperator(String op) {
        return Calculator.isValidOperator(op);
    }

    /**
     * Validates whether the given operator string is supported.
     * Throws an {@link InvalidOperationException} if the operator is not supported.
     *
     * @param op the operator string to validate
     * @throws InvalidOperationException if op is not supported
     */
    public static void validateOperator(String op) {
        if (!isValidOperator(op)) {
            throw new InvalidOperationException("Unknown operator");
        }
    }

    /**
     * Main entry point for the interactive CLI loop.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        logger.debug("Starting SmartCalculator Application...");

        // Polymorphism Demo for Week 5
        logger.info("=== Polymorphism Demo ===");
//        List<Calculable> ops = new ArrayList<>();
//        ops.add(new Addition(10, 4));
//        ops.add(new Subtraction(10, 4));
//        ops.add(new Multiplication(10, 4));
//        ops.add(new Division(10, 4));
//        for (Calculable op : ops) {
//            System.out.println(op.calculate());
//        }
        logger.info("=========================");

        Scanner scanner = new Scanner(System.in);
        logger.info("=== SmartCalculator ===");
        logger.info("Type 'exit' to quit.");

        Calculator calculator = new Calculator();

        while (true) {
            logger.info("Enter first number (or 'exit'): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                logger.debug("User requested application exit.");
                break;
            }

            double firstNumber;
            try {
                firstNumber = Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                logger.debug("Invalid first number input entered: '{}'", input);
                logger.warn("Please enter a valid number.");
                continue;
            }

            logger.info("Enter operator (+ - * / % sqrt): ");
            String operator = scanner.nextLine().trim();
            try {
                validateOperator(operator);
            } catch (InvalidOperationException exception) {
                logger.debug("Invalid operator input entered: '{}'", operator);
                logger.warn(exception.getMessage());
                continue;
            }

            if (operator.equalsIgnoreCase("sqrt")) {
                double result = Calculator.squareRoot(firstNumber);
                if (!Double.isNaN(result)) {
                    logger.info(String.format("Result: %.2f", result));
                }
                continue;
            }

            logger.info("Enter second number: ");
            String secondInput = scanner.nextLine().trim();
            double secondNumber;
            try {
                secondNumber = Double.parseDouble(secondInput);
            } catch (NumberFormatException exception) {
                logger.debug("Invalid second number input entered: '{}'", secondInput);
                logger.warn("Please enter a valid number.");
                continue;
            }

            try {
                Operation operation = switch (operator) {
                    case "+" -> new Addition(firstNumber, secondNumber);
                    case "-" -> new Subtraction(firstNumber, secondNumber);
                    case "*" -> new Multiplication(firstNumber, secondNumber);
                    case "/" -> new Division(firstNumber, secondNumber);
                    case "%" -> new Modulo(firstNumber, secondNumber);
                    default -> throw new InvalidOperationException("Unknown operator");
                };

                double result = calculator.calculate(operation);
                if (!Double.isNaN(result)) {
                    logger.info(String.format("Result: %.2f", result));
                }
            } catch (InvalidOperationException | DivisionByZeroException exception) {
                logger.warn(exception.getMessage());
            }
        }
        logger.info("Goodbye!");
        logger.debug("SmartCalculator Application terminated cleanly.");
        scanner.close();
    }
}
