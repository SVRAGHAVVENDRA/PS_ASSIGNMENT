package com.meridianbank.threads;

/**
 * Task 1 Part C: Demonstration runner for NotificationDispatcher threads.
 */
public class NotificationDemo {
    public static void main(String[] args) {
        System.out.println("Starting notification dispatchers...");

        // Create tasks for ACC001, ACC002, ACC003
        NotificationDispatcher task1 = new NotificationDispatcher("ACC001", "Your monthly statement is ready.");
        NotificationDispatcher task2 = new NotificationDispatcher("ACC002", "Transfer of Rs. 15,000 was successful.");
        NotificationDispatcher task3 = new NotificationDispatcher("ACC003", "Security alert: login from new device.");

        // Wrap tasks in Threads with informative names
        Thread thread1 = new Thread(task1, "Dispatcher-ACC001");
        Thread thread2 = new Thread(task2, "Dispatcher-ACC002");
        Thread thread3 = new Thread(task3, "Dispatcher-ACC003");

        // Start all three threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Join all three threads before proceeding
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting for dispatchers: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        // Print final completion message
        System.out.println("All notifications dispatched.");
    }
}
