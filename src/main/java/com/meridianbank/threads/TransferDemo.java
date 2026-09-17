package com.meridianbank.threads;

/**
 * Task 1 Part A: Thread Creation and Lifecycle Trace.
 */
public class TransferDemo {
    public static void main(String[] args) throws InterruptedException {
        // Thread 1: created by extending Thread (via lambda target)
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " | STARTED");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
