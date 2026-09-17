# SmartCalculator Pseudocode Specification (Phase 7)

This document specifies the algorithmic logic and edge case handling for mathematical utility operations in **SmartCalculator**, focusing on the Square Root operation.

---

## 1. Square Root Operation (`squareRoot`)

### 1.1 Overview
The `squareRoot` function calculates the principal non-negative square root of a given real number. It enforces domain constraints by verifying that the operand is non-negative ($x \ge 0$). Attempting to compute the square root of a negative value triggers an exception to prevent imaginary/NaN states from propagating silently.

### 1.2 Algorithmic Pseudocode

```text
FUNCTION squareRoot(number):
    IF number < 0 THEN
        THROW InvalidOperationException('Cannot sqrt a negative number')
    END IF
    RETURN Math.sqrt(number)
END FUNCTION
```

---

## 2. Decision Logic & Edge Cases

| Input Scenario | Input Value ($x$) | Expected Outcome | System Response |
| :--- | :--- | :--- | :--- |
| **Positive Value** | $25.0$ | $5.0$ | Returns $5.00$ |
| **Zero** | $0.0$ | $0.0$ | Returns $0.00$ |
| **Fractional Decimal** | $0.25$ | $0.5$ | Returns $0.50$ |
| **Negative Value (Edge Case)** | $-9.0$ | Exception | Throws `InvalidOperationException` with `"Cannot sqrt a negative number"` |

---

## 3. Complexity Analysis

- **Time Complexity**: $\mathcal{O}(1)$ — Hardware/FPU floating-point square root operation (typically single CPU instruction, e.g., `FSQRT` or `SQRTSD`).
- **Space Complexity**: $\mathcal{O}(1)$ — Constant memory overhead; executes in-place within register space.

---

## 4. Java 21 Reference Implementation

```java
/**
 * Calculates the square root of a given number.
 *
 * @param number the double value to evaluate
 * @return the square root of the number
 * @throws InvalidOperationException if number is negative
 */
public static double squareRoot(double number) {
    if (number < 0) {
        logger.debug("Negative square root attempted: {}", number);
        throw new InvalidOperationException("Cannot sqrt a negative number");
    }
    return Math.sqrt(number);
}
```
