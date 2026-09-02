package com.savoira.assessment;

/**
 * Task A1: Type Behaviour Demo
 * Demonstrates basic arithmetic behaviour, double casting, and Java's Integer object caching.
 */
public class TypeDemo {
    public static void main(String[] args) {
        System.out.println("=== Task A1: Type Behaviour ===");

        // Block 1: Basic integer arithmetic and floating-point casting
        int a = 9, b = 2;
        
        // Prediction: 4 (Integer division truncates decimal part)
        System.out.println(a / b);
        
        // Prediction: 1 (9 modulo 2 leaves a remainder of 1)
        System.out.println(a % b);
        
        // Prediction: 4.5 (a is cast to double 9.0 before division, prompting double division)
        System.out.println((double) a / b);

        // Block 2: Object reference comparison vs. value equality in Wrapper classes
        Integer x = 100; 
        Integer y = 100;
        Integer p = 200; 
        Integer q = 200;

        // Prediction: true (100 is within the Integer Cache range of [-128, 127], so both reference the same cached object)
        System.out.println(x == y);
        
        // Prediction: false (200 is outside the Integer Cache range, so separate objects are allocated on the heap)
        System.out.println(p == q);
        
        // Prediction: true (equals() compares the wrapped primitive value, which is 200 for both)
        System.out.println(p.equals(q));
    }
}

/*
 * WHY BLOCK 2 PRODUCES DIFFERENT RESULTS FOR 100 and 200:
 * 
 * In Java, the "==" operator compares reference equality (whether two variables point to the same object address
 * in memory) for objects, while "equals()" compares the value equality.
 * 
 * When auto-boxing integer primitives (e.g., Integer x = 100), Java uses Integer.valueOf() under the hood.
 * To optimize memory usage, the JVM maintains an internal cache of Integer objects for values in the range
 * of -128 to 127 (inclusive). 
 * 
 * - For x and y (100): Since 100 is inside the cache range, both x and y are assigned references to the same
 *   pre-instantiated Integer object. Thus, "x == y" evaluates to true.
 * 
 * - For p and q (200): Since 200 is outside the cache range, the JVM must instantiate two distinct Integer
 *   objects on the heap. Thus, p and q point to different memory addresses, and "p == q" evaluates to false.
 * 
 * - For p.equals(q): The equals() method in the Integer class is overridden to compare the actual primitive
 *   int values (200 vs 200), rather than their memory references. Therefore, it evaluates to true.
 */
