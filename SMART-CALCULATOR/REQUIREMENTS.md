# Requirements Document & Solution Design

## 1. 5 Whys Analysis Technique

**Problem Statement:** Users (students, shopkeepers, vendors) often make manual calculation mistakes or need a reliable, lightweight tool for everyday arithmetic.

1. **Why do users make errors during everyday calculations?**
   - Because doing math mentally or on paper is prone to human error when handling multiple numbers or decimal values.
2. **Why don't users use existing complex software tools?**
   - Because bloated software applications take time to open, require internet access, or have confusing interfaces.
3. **Why is a dedicated command-line calculator effective?**
   - Because a CLI tool opens instantly, runs locally, consumes minimal system resources, and gives immediate results.
4. **Why do basic calculators sometimes fail user expectations?**
   - Because they lack proper input validation, crash on invalid inputs (like dividing by zero), or close automatically after a single calculation.
5. **Why is the SmartCalculator solution necessary?**
   - It provides a simple, continuous interactive environment with robust error handling and basic arithmetic operations (+, -, *, /, %) that stays open until explicitly exited.

---

## 2. Functional Requirements

- **FR-1: Basic Arithmetic Operations**
  - The system must support Addition (`+`), Subtraction (`-`), Multiplication (`*`), Division (`/`), and Modulo (`%`).
- **FR-2: User Input Processing**
  - The system must use Java `Scanner` to prompt and read user input for numbers and operators.
- **FR-3: Operation Selection**
  - The system must evaluate the chosen operator using Java `switch` expressions/statements.
- **FR-4: Continuous Mode**
  - The system must run in a continuous loop until the user types `exit` or selects the quit option.
- **FR-5: Error Handling & Input Validation**
  - The system must check for division/modulo by zero and display a friendly message instead of crashing.
  - The system must handle invalid operator entries gracefully.

---

## 3. Non-Functional Requirements

- **NFR-1: Usability**
  - Clear, straightforward console output with intuitive prompts.
- **NFR-2: Performance & Speed**
  - Instantaneous execution time (< 50ms) for arithmetic calculations.
- **NFR-3: Accuracy & Precision**
  - Calculations must maintain standard `double` precision floating-point accuracy.
- **NFR-4: Reliability**
  - Zero crashes on edge cases (e.g., zero divisor, unexpected character input).

---

## 4. Feature Prioritization (MoSCoW Matrix)

| Priority | Feature Description | Included in Phase 3 |
| :--- | :--- | :---: |
| **Must Have** | Basic operations (`+`, `-`, `*`, `/`, `%`), `switch` logic | Yes |
| **Must Have** | Zero-division guard & invalid operator warning | Yes |
| **Should Have** | Continuous loop mode until explicit exit | Yes |
| **Could Have** | History tracking of previous calculations | Future Phase |
| **Won't Have** | Graphical User Interface (GUI) | Out of scope |

---

## 5. User Stories & Acceptance Criteria

### User Story 1: Basic Calculations
> **As a** user  
> **I want to** enter two numbers and an operator  
> **So that** I can get the accurate arithmetic result immediately.

**Acceptance Criteria:**
- Given numbers `10` and `5` with operator `+`, output should be `15.0`.
- Given numbers `20` and `4` with operator `/`, output should be `5.0`.
- Given numbers `17` and `5` with operator `%`, output should be `2.0`.

### User Story 2: Continuous Execution
> **As a** user  
> **I want** the program to keep running after completing a calculation  
> **So that** I can perform multiple calculations without restarting the application.

**Acceptance Criteria:**
- After displaying the result, the application prompts: "Do you want to perform another calculation? (yes/no)".
- Entering `yes` restarts the input prompt; entering `no` exits cleanly.

### User Story 3: Division by Zero Safety
> **As a** user  
> **I want** the calculator to catch division by zero  
> **So that** the program prints an error message instead of crashing with an exception.

**Acceptance Criteria:**
- Entering `10 / 0` prints `"Error: Division by zero is not allowed."` and prompts for the next input cleanly.
