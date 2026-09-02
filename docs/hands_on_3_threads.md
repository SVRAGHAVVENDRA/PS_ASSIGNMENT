# PJP Week 6: Hands-On 3 Exercise — Basics of Threads in Java

## Business Scenario
**Context**: Meridian Retail Bank Core Banking Platform.
The payment service concurrency bottleneck is solved using thread-safe multithreading primitives.

---

## Task 1: Thread Creation & Lifecycle Trace

### Part A — Trace the Output

Starter code analyzed from `TransferDemo.java`:

```java
package com.meridianbank.threads;

public class TransferDemo {
    public static void main(String[] args) throws InterruptedException {
        // Thread 1: created by extending Thread (target runnable/lambda)
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " | STARTED");
            try { Thread.sleep(100); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println(Thread.currentThread().getName() + " | DONE");
        }, "TransferWorker-1");

        // Thread 2: created by implementing Runnable
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " | STARTED");
            System.out.println(Thread.currentThread().getName() + " | DONE");
        };
        Thread t2 = new Thread(task, "AuditLogger-1");

        System.out.println("Main | Before start");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Main | After join");
    }
}
```

#### Expected Output Table

| Expected output line (write one line per row) | Explanation |
| :--- | :--- |
| `Main | Before start` | Printed synchronously by the `main` thread before starting either worker thread. |
| `TransferWorker-1 | STARTED` | `t1.start()` invokes `TransferWorker-1`, which prints its started message and then enters `Thread.sleep(100)`. |
| `AuditLogger-1 | STARTED` | `t2.start()` starts `AuditLogger-1` concurrently. *(Note: depending on OS scheduling, t2 may print before or after t1 starts, but before t1 wakes up from sleep).* |
| `AuditLogger-1 | DONE` | `AuditLogger-1` has no sleep delay and completes its task immediately. |
| `TransferWorker-1 | DONE` | After the 100ms sleep expires, `TransferWorker-1` resumes and prints its completion message. |
| `Main | After join` | Guaranteed to print strictly last because `t1.join()` and `t2.join()` hold the `main` thread until both workers terminate. |

---

### Part B — Conceptual Questions (6 Marks)

#### B1. What is the state of `t1` while it is inside `Thread.sleep(100)`?
**Answer**:
The state of `t1` is **`TIMED_WAITING`**. When a thread executes a timed pause such as `Thread.sleep(millis)`, `Object.wait(timeout)`, or `Thread.join(timeout)`, the JVM moves the thread into the `TIMED_WAITING` state until the specified interval elapses or an interruption occurs.

#### B2. Can 'Main | After join' print BEFORE both workers print 'DONE'? Explain in one sentence.
**Answer**:
**No**, because `t1.join()` and `t2.join()` cause the calling `main` thread to block and wait until both `t1` and `t2` have fully terminated their execution.

#### B3. A colleague suggests replacing `t1.start()` with `t1.run()`. What is the consequence?
**Answer**:
Replacing `t1.start()` with `t1.run()` will execute the `run()` method synchronously on the current thread (`main`) instead of spawning a new asynchronous thread; this eliminates concurrency, executes the 100ms sleep on `main`, and prints `main | STARTED` instead of `TransferWorker-1 | STARTED`.

---

### Part C — Code Implementation (6 Marks)

The task requires creating `NotificationDispatcher` implementing `Runnable` and running a demo with 3 threads.

#### Class: `NotificationDispatcher.java`
- Implements `java.lang.Runnable`.
- Accepts `accountId` and `message` in constructor.
- In `run()`:
  1. Prints `[thread-name] | Dispatching to [accountId]`
  2. Calls `Thread.sleep(50)` to simulate network latency.
  3. Prints `[thread-name] | Sent: [message]`

#### Execution Console Output (`NotificationDemo.java`)
```text
Starting notification dispatchers...
Dispatcher-ACC001 | Dispatching to ACC001
Dispatcher-ACC002 | Dispatching to ACC002
Dispatcher-ACC003 | Dispatching to ACC003
Dispatcher-ACC001 | Sent: Your monthly statement is ready.
Dispatcher-ACC002 | Sent: Transfer of Rs. 15,000 was successful.
Dispatcher-ACC003 | Sent: Security alert: login from new device.
All notifications dispatched.
```
*(All three worker threads are guaranteed to complete before "All notifications dispatched." due to `thread.join()` synchronization).*
