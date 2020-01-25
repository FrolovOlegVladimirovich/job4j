package ru.job4j.multithreading.threadpool.emailnotification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Email notifier using an ExecutorService.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class EmailNotification {
    private final ExecutorService pool;

    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Sending email, using thread pool.
     * @param user User.
     */
    public void emailTo(User user) {
        var email = user.getEmail();
        var userName = user.getUserName();
        var subject = String.format("Notification %s to email %s", userName, email);
        var body = String.format("Add a new event to %s", userName);
        pool.submit(() -> send(subject, body, email));
    }

    /**
     * Closes threads in the pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String subject, String body, String email) {
    }
}