# Assessment 4 · Task D2.5: Runtime Environment Reflection

**Topic**: Comparing Application Execution in the Browser (HTML/CSS) vs. Terminal (Java CLI)  
**Context**: Meridian Retail Bank / SmartCalculator  

---

## Reflection

In the Java terminal environment, our application runs as a stateful, compiled computational engine capable of interactive procedural loops, real-time numeric calculations, and strict type-checked exception handling (e.g., catching `InvalidDenominationException` or maintaining an in-memory ledger balance). 

Conversely, the browser version built with pure HTML5 and CSS operates as a declarative presentation layer; while it offers rich responsive layouts, visual affordances, and native constraint validation (such as `min`, `max`, and `pattern`), it completely lacks a computational runtime and state management. 

What is fundamentally missing from the browser version is the active calculation logic and transaction-processing pipeline that Java provides—without a client-side scripting language like JavaScript or a backend servlet to process form submissions, the browser can only validate input syntax and display static data rather than executing actual deposits, interest formulas, or fund transfers.
