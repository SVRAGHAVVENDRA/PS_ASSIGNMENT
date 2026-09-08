package com.savoira.w7;

/**
 * Contains a corrected version of the buggy findLargest method,
 * with each fix documented above the corrected line.
 */
public class BugFixer {

    static double findLargest(double[] amounts) {
        // Bug 1 fix: initializing max to 0 is wrong because if all amounts are
        // negative, the method would incorrectly return 0 instead of the actual
        // largest (negative) value. Initialize max to the first array element instead.
        double max = amounts[0];

        // Bug 2 fix: the loop condition used "<=" which allows i to reach
        // amounts.length, causing an ArrayIndexOutOfBoundsException when
        // accessing amounts[amounts.length]. Changed to "<" so the loop only
        // accesses valid indices 0..amounts.length-1.
        for (int i = 0; i < amounts.length; i++) {
            if (amounts[i] > max) {
                max = amounts[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        double[] amounts = {1200.50, 4500.75, 300.0, 9800.25, 250.0};
        System.out.println("Largest amount: " + findLargest(amounts));

        double[] negativeAmounts = {-500.0, -1200.0, -50.0};
        System.out.println("Largest amount (all negative): " + findLargest(negativeAmounts));
    }
}

