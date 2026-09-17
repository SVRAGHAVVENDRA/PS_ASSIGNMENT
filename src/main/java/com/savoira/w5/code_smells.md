# Code Smells Analysis and Refactoring

This document identifies the code smells present in the following method snippet and provides a clean refactoring:

```java
void process(List<Loan> l) {
    for(int i=0;i<l.size();i++){
        if(l.get(i).principal>500000&&l.get(i).principal<2000000)
            System.out.println(l.get(i).loanId);
    }
}
```

---

## Identified Code Smells

### 1. Feature Envy / Violation of Encapsulation (Direct Field Access)
* **Smell**: The method is query-heavy on the `Loan` class. It directly accesses internal fields (`principal` and `loanId`) of the `Loan` instances. This violates encapsulation principles and demonstrates "Feature Envy," where a method in one class is more interested in the data of another class than its own.
* **Impact**: Changes to the internal representation of `Loan` fields will break the `process` method. It also prevents the `Loan` class from controlling and validating its own state.

### 2. Magic Numbers
* **Smell**: The values `500000` and `2000000` are hardcoded literals without any context or named variables.
* **Impact**: If these audit thresholds change in the future, developers must search and replace them throughout the codebase, which is error-prone.

### 3. Inefficient List Iteration (Index-based Loop)
* **Smell**: The method uses a traditional `for` loop with index access (`l.get(i)`).
* **Impact**: If the input list `l` is a `LinkedList`, calling `get(i)` inside a loop results in quadratic complexity $O(N^2)$ because `LinkedList` must traverse from the head to index `i` on each call. It is also less readable than modern iteration patterns.

---

## Refactored Solution

### Step 1: Add Helper/Encapsulation Methods in `Loan.java`

To keep logic close to the data, we encapsulate the range check within the `Loan` class:

```java
// Inside Loan.java
public static final double MIN_AUDIT_LIMIT = 500000.0;
public static final double MAX_AUDIT_LIMIT = 2000000.0;

public boolean isPrincipalInAuditRange() {
    return this.principal > MIN_AUDIT_LIMIT && this.principal < MAX_AUDIT_LIMIT;
}
```

### Step 2: Rewrite `process` Method Cleanly

Using the enhanced `for-each` loop (or Java Streams) and proper encapsulation:

#### Option A: Enhanced For-Each (Recommended for simplicity & readability)
```java
void process(List<Loan> loans) {
    if (loans == null) return;
    
    for (Loan loan : loans) {
        if (loan.isPrincipalInAuditRange()) {
            System.out.println(loan.getLoanId());
        }
    }
}
```

#### Option B: Java Streams API (Clean functional approach)
```java
void process(List<Loan> loans) {
    if (loans == null) return;

    loans.stream()
         .filter(Loan::isPrincipalInAuditRange)
         .map(Loan::getLoanId)
         .forEach(System.out::println);
}
```
