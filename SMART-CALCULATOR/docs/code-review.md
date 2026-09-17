# SmartCalculator Code Review Checklist & Self-Review (Phase 6)

This document contains the standard checklist used for code reviews in the SmartCalculator project, along with the self-review notes for Phase 6.

---

## Code Review Checklist

Before committing any code, verify that it complies with the following 5 requirements:

1. **Exception Safety**:
   - Are edge cases (such as division by zero, invalid bounds, modulo zero) handled by throwing specific custom exceptions (e.g., [DivisionByZeroException](file:///d:/SMART-CALCULATOR/src/main/java/com/savoira/DivisionByZeroException.java)) instead of failing silently or returning `Double.NaN`?
   - Is all exception hierarchy logically subclassed from standard Java exception templates (`RuntimeException` or `ArithmeticException`)?

2. **Precision & Scale**:
   - Are calculations sensitive to floating-point drift (like division or compound math) using `BigDecimal` with explicit scaling and rounding modes (`RoundingMode.HALF_UP`) instead of standard primitive `double` arithmetic?

3. **Input Validation**:
   - Is user input thoroughly validated and parsed inside try-catch blocks?
   - Do parsing failures display clean, human-readable error messages without leaking stack traces or crashing the interactive loop?

4. **Javadoc & API Contracts**:
   - Does every new public class, interface, constructor, and method contain clean Javadoc comments?
   - Are `@param`, `@return`, and `@throws` tags present and accurately defined?

5. **Code Hygiene & Conventions**:
   - Are class names in `PascalCase`, and method/variable names in `camelCase`?
   - Are single-letter variable names avoided (except for standard loop counters)?
   - Is the code formatted correctly (e.g., standard Maven/IntelliJ format)?

---

## Phase 6 Self-Review & Improvement Notes

During the implementation of Phase 6, a code self-review was conducted against the checklist:

### Item Evaluated: *Precision & Scale (Item #2)*
* **Initial Code**: The division logic in the [Division](file:///d:/SMART-CALCULATOR/src/main/java/com/savoira/Division.java) class was implemented using primitive arithmetic: `return a / b;`. This introduces potential floating-point representation drift (e.g., `1.0 / 3.0` resulting in `0.3333333333333333`).
* **Code Improvement**: Migrated [Division.java](file:///d:/SMART-CALCULATOR/src/main/java/com/savoira/Division.java) to use `BigDecimal` for its calculations. Explicitly configured a scale of `10` decimal places and set the rounding mode to `RoundingMode.HALF_UP` to guarantee exact mathematical results and eliminate binary representation inaccuracies.
