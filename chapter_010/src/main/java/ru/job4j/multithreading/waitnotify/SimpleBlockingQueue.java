package ru.job4j.multithreading.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Bounded blocking queue.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue;
    private final int size;
    @GuardedBy("this")
    private int added = 0;

    /**
     * @param size of queue.
     */
    public SimpleBlockingQueue(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    /**
     * Offers value to queue.
     * If the queue is busy current thread is waiting for free space.
     * @param value Value.
     */
    public synchronized void offer(T value) {
        while (added == size) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        ++added;
        this.notifyAll();
    }

    /**
     * Polls value from the queue.
     * If the queue is empty, the current thread is waiting for new values.
     * @return value if the queue is not empty.
     */
    public synchronized T poll() {
        while (added == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        var element = queue.poll();
        --added;
        this.notifyAll();
        return element;
    }
}