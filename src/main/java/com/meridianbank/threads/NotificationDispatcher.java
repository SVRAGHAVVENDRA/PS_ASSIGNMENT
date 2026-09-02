package com.meridianbank.threads;

/**
 * Task 1 Part C: Notification dispatcher executing asynchronously via Runnable.
 */
public class NotificationDispatcher implements Runnable {
    private final String accountId;
    private final String message;

    public NotificationDispatcher(String accountId, String message) {
        this.accountId = accountId;
        this.message = message;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        // (1) print [thread-name] | Dispatching to [accountId]
        System.out.println(threadName + " | Dispatching to " + accountId);

        // (2) sleep 50ms to simulate network latency
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(threadName + " was interrupted while dispatching.");
            return;
        }

        // (3) print [thread-name] | Sent: [message]
        System.out.println(threadName + " | Sent: " + message);
    }

    public String getAccountId() {
        return accountId;
    }

    public String getMessage() {
        return message;
    }
}
