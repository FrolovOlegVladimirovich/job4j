package ru.job4j.multithreading.threadpool;

import ru.job4j.multithreading.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Thread pool.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class ThreadPool {
    private final int maxThreads = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    /**
     * @param tasksSize - size of SimpleBlockingQueue.
     */
    public ThreadPool(int tasksSize) {
        tasks = new SimpleBlockingQueue<>(tasksSize);
    }

    /**
     * Adds a job to be processed by a thread pool.
     * @param job Some runnable job.
     * @throws InterruptedException Exception.
     */
    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
        if (threads.size() < maxThreads) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * Shutdown all threads in the thread pool.
     */
    public void shutdown() {
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }
}