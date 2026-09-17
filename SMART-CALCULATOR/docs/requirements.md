# SmartCalculator Requirements & Design (Phase 2)

## 1. 5 Whys Analysis

**Initial Question:** Why does a user need a calculator app?

1. **Why does a user need a calculator app?**
   - Because mental and manual calculations are slow and prone to errors.
2. **Why are manual calculations error-prone?**
   - Because complex operations or repeated arithmetic introduce cognitive fatigue and floating-point mistranslations on paper.
3. **Why not use existing system calculators?**
   - Because users need a simple, lightweight CLI application that runs locally without distracting GUIs or heavy dependencies.
4. **Why is a structured command-line calculator effective?**
   - It provides rapid input processing via standard I/O and immediate double-precision results.
5. **Why build SmartCalculator incrementally?**
   - To establish a robust, testable, and extensible foundation starting with core CLI operations and evolving into clean Object-Oriented design.

**Insight:** A simple CLI calculator must prioritize usability, input safety (handling zero division gracefully), and continuous loop capability before adding advanced features.

---

## 2. Functional Requirements

- **FR1:** The calculator shall support `+`, `-`, `*`, `/`, `%` operations.
- **FR2:** The user shall enter two numbers and an operator via the CLI.
- **FR3:** The calculator shall run in a loop until the user types `'exit'`.
- **FR4:** Division by zero shall display a clear error message, not crash.
- **FR5:** The calculator shall display results to two decimal places (`%.2f`).

---

## 3. Non-Functional Requirements

- **NF1:** Each calculation shall complete and display within 1 second (< 50ms).
- **NF2:** Error messages shall be human-readable and user-friendly.

---

## 4. Scope & Exclusions (What We Will NOT Build)

- No Graphical User Interface (GUI) in early CLI phases.
- No external databases or remote server backends.
- No heavy frameworks (pure Java 21 standard library + Scanner).

---

## 5. Planned Class Structure & Architecture

```text
com.savoira/
└── SmartCalculator.java    <-- CLI entry point, continuous interactive loop, and calculation logic
```
