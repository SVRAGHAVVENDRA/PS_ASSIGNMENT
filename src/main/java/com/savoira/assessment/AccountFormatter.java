package com.savoira.assessment;

import java.util.Locale;

/**
 * Task B2: Account Formatter
 * Formats account summaries with name normalization, precision control, and currency symbol prefixing.
 */
public class AccountFormatter {

    /**
     * Formats the holder name, balance, and account type into a standardized summary.
     *
     * @param name        the holder name
     * @param balance     the account balance
     * @param accountType the type of account (e.g. SAVINGS, CURRENT)
     * @return the formatted summary string
     */
    public static String formatAccountSummary(String name, double balance, String accountType) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Holder name cannot be null or blank.");
        }
        if (accountType == null || accountType.isBlank()) {
            throw new IllegalArgumentException("Account type cannot be null or blank.");
        }

        String uppercaseName = name.toUpperCase(Locale.ROOT);
        // Format balance to exactly 2 decimal places
        String formattedBalance = String.format(Locale.ROOT, "%.2f", balance);

        return "Account Holder: " + uppercaseName + " | Type: " + accountType + " | Balance: ₹" + formattedBalance;
    }

    public static void main(String[] args) {
        System.out.println("=== Task B2: Account Formatter ===");

        // Call 1
        String summary1 = formatAccountSummary("Priya Sharma", 45200.50, "SAVINGS");
        System.out.println(summary1);

        // Call 2
        String summary2 = formatAccountSummary("amit patel", 10950.00, "CURRENT");
        System.out.println(summary2);

        // Call 3
        String summary3 = formatAccountSummary("Dr. John Doe", 250000.759, "FIXED DEPOSIT");
        System.out.println(summary3);
    }
}
