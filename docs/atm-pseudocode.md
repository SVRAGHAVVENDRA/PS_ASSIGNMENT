# Assessment 4 · Task A1.1: ATM Withdrawal Algorithm Pseudocode

**Context**: Meridian Retail Bank  
**Feature**: Automated Teller Machine (ATM) Cash Withdrawal Engine  

---

## Algorithm Specifications & Constraints
- **Initial Account Balance**: ₹10,000
- **Minimum Withdrawal Limit**: ₹500
- **Maximum Withdrawal Limit**: ₹20,000
- **Cash Cartridge Dispense Denomination**: Multiples of ₹500
- **Security Threshold**: Maximum 3 consecutive failed attempts before triggering an immediate card lock.

---

## Pseudocode

```text
BEGIN ATM_WITHDRAWAL_SESSION

    // Initialization
    SET balance = 10000.0
    SET attempts = 0
    CONSTANT MAX_ATTEMPTS = 3
    CONSTANT MIN_WITHDRAWAL = 500.0
    CONSTANT MAX_WITHDRAWAL = 20000.0
    CONSTANT DENOMINATION = 500.0

    DISPLAY "=== Welcome to Meridian Retail Bank ATM ==="
    DISPLAY "Available Ledger Balance: ₹" + balance

    // Retry Loop
    WHILE attempts < MAX_ATTEMPTS DO

        DISPLAY "Enter withdrawal amount (Attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS + "):"
        READ user_input

        // Validate numeric format
        IF user_input IS NOT NUMERIC THEN
            DISPLAY "Error: Invalid numeric input. Please enter a valid number."
            SET attempts = attempts + 1
            CONTINUE
        END IF

        SET amount = CONVERT_TO_NUMBER(user_input)

        // Rule 1: Minimum withdrawal threshold check
        IF amount < MIN_WITHDRAWAL THEN
            DISPLAY "Error: Minimum withdrawal amount is ₹" + MIN_WITHDRAWAL + ". Requested: ₹" + amount
            SET attempts = attempts + 1

        // Rule 2: Maximum withdrawal threshold check
        ELSE IF amount > MAX_WITHDRAWAL THEN
            DISPLAY "Error: Maximum withdrawal amount is ₹" + MAX_WITHDRAWAL + ". Requested: ₹" + amount
            SET attempts = attempts + 1

        // Rule 3: Denomination multiple check
        ELSE IF (amount MOD DENOMINATION) != 0 THEN
            DISPLAY "Error: Amount must be in multiples of ₹" + DENOMINATION + ". Requested: ₹" + amount
            SET attempts = attempts + 1

        // Rule 4: Balance sufficiency check
        ELSE IF amount > balance THEN
            SET shortfall = amount - balance
            DISPLAY "Error: Insufficient balance. Available: ₹" + balance + ", Shortfall: ₹" + shortfall
            SET attempts = attempts + 1

        // Rule 5: All checks passed - execute withdrawal transaction
        ELSE
            SET balance = balance - amount
            DISPLAY "Withdrawal successful! Dispensed: ₹" + amount
            DISPLAY "Updated Account Balance: ₹" + balance
            EXIT_SESSION
        END IF

    END WHILE

    // Security lockout after exceeding maximum allowed attempts
    IF attempts >= MAX_ATTEMPTS THEN
        DISPLAY "Card locked."
    END IF

END ATM_WITHDRAWAL_SESSION
```
