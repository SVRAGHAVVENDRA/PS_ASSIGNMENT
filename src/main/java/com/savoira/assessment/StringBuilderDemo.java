package com.savoira.assessment;

/**
 * Task A2: StringBuilder Performance Demo
 * Implements a report builder method using StringBuilder and explains string concatenation performance implications.
 */
public class StringBuilderDemo {

    /**
     * Builds a single formatted report string from an array of items.
     * Output format: 'Report: item1 | item2 | item3'
     *
     * @param items array of string items (cannot be null)
     * @return the formatted report string
     */
    public static String buildReport(String[] items) {
        if (items == null) {
            throw new IllegalArgumentException("Items array cannot be null.");
        }
        if (items.length == 0) {
            return "Report: ";
        }

        StringBuilder sb = new StringBuilder("Report: ");
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i]);
            if (i < items.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Task A2: StringBuilder Demo ===");

        // Array of 5 bank transaction descriptions
        String[] transactions = {
            "Salary Deposit (+Rs. 75,000.00)",
            "Grocery Purchase (-Rs. 3,420.50)",
            "Electric Bill Utility (-Rs. 1,890.00)",
            "ATM Cash Withdrawal (-Rs. 5,000.00)",
            "Online Transfer to Mom (-Rs. 10,000.00)"
        };

        // Call buildReport and print
        String report = buildReport(transactions);
        System.out.println(report);
    }
}

/*
 * WHY BUILDER IS PREFERRED OVER STRING CONCATENATION (+):
 * 
 * Strings in Java are immutable. Every time two String objects are concatenated using the "+" operator
 * (or inside a loop), Java is forced to create a new String object in memory and copy all of the characters
 * from the previous strings into the new one.
 * 
 * If concatenation is performed inside a loop of size N:
 * - A new String object is allocated at each iteration.
 * - The character copying operation takes time proportional to the length of the string accumulated so far.
 * - This leads to an O(N^2) time complexity, which causes major performance bottlenecks and heavy garbage collection pressure.
 * 
 * In contrast, StringBuilder is a mutable character sequence. It maintains an internal, expandable buffer (char array).
 * Appending to a StringBuilder modifies the buffer in place without creating new intermediate String objects.
 * Once the loop is complete, we call toString() to generate the final String.
 * This runs in O(N) time complexity, which is significantly faster and uses far less memory.
 */
