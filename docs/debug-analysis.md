# Assessment 4 · Task B1.2: Debug Analysis for `sumEvens`

**Objective**: Identify, explain, and correct the flaws in the `sumEvens(int n)` algorithm.  
**Target Specification**: Return the sum of all even integers from $1$ to $n$. For $n = 10$, the expected result is $2 + 4 + 6 + 8 + 10 = 30$.

---

## 1. Problem Code Analysis

```java
public static int sumEvens(int n) { 
    int sum = 1;          // Bug 1: Initialized to 1 instead of 0
    for (int i = 1; i <= n; i++) { 
        if (i % 2 == 1)   // Bug 2: Wrong condition (selects odd numbers)
            sum += i; 
    } 
    return sum; 
} 
```

When evaluated with $n = 10$, the buggy implementation returned **26** instead of **30**.

---

## 2. Root Cause Analysis

### Bug 1: Incorrect Accumulator Initialization (`int sum = 1;`)
- **Flaw**: The accumulator variable `sum` was initialized to `1`. In arithmetic, the identity element for addition is $0$ ($a + 0 = a$). Starting at `1` introduces an artificial +1 bias to any calculated total. Even for an edge case where no numbers are added (e.g., $n = 1$), the method would erroneously return `1`.
- **Identification**: Reviewing variable declaration and baseline invariants. An accumulator intended to sum values must always start at `0`.
- **Fix**: Change initialization to `int sum = 0;`.

### Bug 2: Inverted Parity Condition (`if (i % 2 == 1)`)
- **Flaw**: The expression `i % 2` computes the remainder of integer division by 2. When `i % 2 == 1`, the number has a non-zero remainder, which is the mathematical definition of an **odd** integer. As a result, the loop was accumulating odd numbers ($1, 3, 5, 7, 9$), producing $1 + 3 + 5 + 7 + 9 = 25$. Adding the initial `sum = 1` yielded $26$.
- **Identification**: Running a manual trace for $n = 10$:
  - Odd numbers in range: $\{1, 3, 5, 7, 9\} \rightarrow \sum = 25$.
  - With `sum = 1`: $25 + 1 = 26$. This precisely explained the output of 26.
  - Even numbers in range: $\{2, 4, 6, 8, 10\} \rightarrow \sum = 30$.
- **Fix**: Update the conditional statement to check for divisibility by 2: `if (i % 2 == 0)`.

---

## 3. Corrected Code

```java
public static int sumEvensFixed(int n) { 
    int sum = 0;          // Fix 1: Initialize to additive identity 0
    for (int i = 1; i <= n; i++) { 
        if (i % 2 == 0)   // Fix 2: Check for even numbers
            sum += i; 
    } 
    return sum; 
} 
```

---

## 4. Verification

Running `BugFix.main` on JDK 21 confirms:
```text
=== Task B1: sumEvens Debugging Output for n = 10 ===
Buggy Implementation Output : 26 (Expected: 30)
Fixed Implementation Output : 30 (Expected: 30)
```
The fixed implementation matches the expected mathematical specification.
