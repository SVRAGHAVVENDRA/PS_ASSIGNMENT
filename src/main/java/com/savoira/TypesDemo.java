package com.savoira;

/**
 * Week 3 Assignment 1: Java Fundamentals & Types
 * Meridian Retail Bank
 */
public class TypesDemo {

    public static void main(String[] args) {
        System.out.println("=== Block 1: Integer Division & Type Casting ===");
        // Block 1: integer division
        int a = 17, b = 5;
        System.out.println(a / b);
        System.out.println(a % b);
        System.out.println((double) a / b);

        System.out.println("\n=== Block 2: Integer Cache ===");
        // Block 2: Integer cache
        Integer x = 127; Integer y = 127;
        Integer p = 200; Integer q = 200;
        System.out.println(x == y);
        System.out.println(p == q);
        System.out.println(p.equals(q));

        // Question 1 Explanation:
        // Java caches Integer objects in the range -128 to 127, so x and y reference the same cached object in memory (== returns true), whereas p and q (200) fall outside the cache range, creating distinct object instances in memory (== compares reference equality, returning false).

        System.out.println("\n=== StringBuilder Performance Demo ===");
        demonstrateStringBuilder();
    }

    public static void demonstrateStringBuilder() {
        // Question 2 Explanation:
        // StringBuilder is preferred over String concatenation inside a loop because String objects are immutable in Java, so concatenation creates a new String object on every iteration causing unnecessary memory churn and GC overhead, whereas StringBuilder appends characters in-place using a mutable internal array.

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append("Transaction_").append(i).append(" ");
        }
        System.out.println("Concatenated Result: " + sb.toString().trim());
    }
}
