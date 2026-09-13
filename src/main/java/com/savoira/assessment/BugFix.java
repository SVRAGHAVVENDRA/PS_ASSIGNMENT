package com.savoira.assessment;

/**
 * Demonstrates debugging and fixing the sumEvens algorithm for Assessment 4 Task B1.
 */
public class BugFix {

    /**
     * Original buggy implementation from the problem statement.
     * Contains two bugs:
     * 1. Initial sum set to 1 instead of 0.
     * 2. Parity condition checks for odd numbers (i % 2 == 1) instead of even numbers.
     */
    public static int sumEvensBuggy(int n) {
        int sum = 1;          // Bug 1: Initialised incorrectly
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1)   // Bug 2: Wrong condition (sums odd numbers)
                sum += i;
        }
        return sum;
    }

    /**
     * Corrected implementation returning the sum of all even numbers from 1 to n.
     */
    public static int sumEvensFixed(int n) {
        int sum = 0;          // Fix 1: Initialize sum to neutral additive identity (0)
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0)   // Fix 2: Check if i is divisible by 2 with remainder 0
                sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 10;
        int buggyResult = sumEvensBuggy(n);
        int fixedResult = sumEvensFixed(n);

        System.out.println("=== Task B1: sumEvens Debugging Output for n = " + n + " ===");
        System.out.println("Buggy Implementation Output : " + buggyResult + " (Expected: 30)");
        System.out.println("Fixed Implementation Output : " + fixedResult + " (Expected: 30)");
    }
}
