package com.savoira.assessment;

/**
 * Task B1: Enhanced Switch Expression
 * Classifies transaction types using Java 21's arrow switch expressions and conditional transfer rules.
 */
public class TransactionClassifier {

    /**
     * Classifies a transaction based on its type and amount.
     * Uses Java 21's enhanced switch expression.
     *
     * @param type   the type of transaction (CREDIT, DEBIT, TRANSFER, WITHDRAWAL)
     * @param amount the transaction amount
     * @return a description of the transaction category
     * @throws IllegalArgumentException if the transaction type is unknown or null
     */
    public static String classifyTransaction(String type, double amount) {
        if (type == null) {
            throw new IllegalArgumentException("Transaction type cannot be null.");
        }

        return switch (type.toUpperCase()) {
            case "CREDIT" -> "Income — positive cash flow";
            case "DEBIT" -> "Expense — deducted from balance";
            case "TRANSFER" -> {
                if (amount > 10000.0) {
                    yield "Large Transfer — requires OTP";
                } else {
                    yield "Standard Transfer";
                }
            }
            case "WITHDRAWAL" -> "Cash Withdrawal";
            default -> throw new IllegalArgumentException("Unknown transaction type: " + type);
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Task B1: Transaction Classifier ===");

        // Test Case 1: CREDIT
        System.out.println("CREDIT: " + classifyTransaction("CREDIT", 5000.0));

        // Test Case 2: DEBIT
        System.out.println("DEBIT: " + classifyTransaction("DEBIT", 1200.0));

        // Test Case 3: TRANSFER <= 10000
        System.out.println("TRANSFER (8000): " + classifyTransaction("TRANSFER", 8000.0));

        // Test Case 4: TRANSFER > 10000
        System.out.println("TRANSFER (15000): " + classifyTransaction("TRANSFER", 15000.0));

        // Test Case 5: WITHDRAWAL
        System.out.println("WITHDRAWAL: " + classifyTransaction("WITHDRAWAL", 2000.0));

        // Test Case 6: Unknown Type (should trigger IllegalArgumentException)
        try {
            System.out.println("Attempting unknown type 'DEPOSIT'...");
            classifyTransaction("DEPOSIT", 100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }
    }
}
