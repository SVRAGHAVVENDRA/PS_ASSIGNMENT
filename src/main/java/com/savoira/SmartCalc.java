package com.savoira;

import java.util.Scanner;

/*
 * Expected Test Case Outputs:
 * 
 * Test Case 1: Standard Addition
 * Inputs: 10, 3, +
 * Output: Result: 13.00
 * 
 * Test Case 2: Division by Zero
 * Inputs: 10, 0, /
 * Output: Error: Division by zero
 * 
 * Test Case 3: Unknown Operator
 * Inputs: 10, 3, ^
 * Output: Error: Unknown operator '^'
 */

public class SmartCalc {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Meridian Retail Bank - CLI Calculator ===");

        while (true) {
            System.out.print("\nEnter first number (or type 'exit' to quit): ");
            if (!scanner.hasNextDouble()) {
                String input = scanner.next();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting calculator.");
                    break;
                }
                System.out.println("Error: Invalid number format");
                continue;
            }
            double num1 = scanner.nextDouble();

            System.out.print("Enter second number: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Error: Invalid number format");
                scanner.next();
                continue;
            }
            double num2 = scanner.nextDouble();

            System.out.print("Enter operator (+, -, *, /, %): ");
            String operator = scanner.next();

            calculate(num1, num2, operator);
        }
        scanner.close();
    }

    public static void calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+" -> System.out.printf("Result: %.2f%n", num1 + num2);
            case "-" -> System.out.printf("Result: %.2f%n", num1 - num2);
            case "*" -> System.out.printf("Result: %.2f%n", num1 * num2);
            case "/" -> {
                if (num2 == 0) {
                    System.out.println("Error: Division by zero");
                } else {
                    System.out.printf("Result: %.2f%n", num1 / num2);
                }
            }
            case "%" -> {
                if (num2 == 0) {
                    System.out.println("Error: Division by zero");
                } else {
                    System.out.printf("Result: %.2f%n", num1 % num2);
                }
            }
            default -> System.out.println("Error: Unknown operator '" + operator + "'");
        }
    }
}
