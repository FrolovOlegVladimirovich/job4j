package ru.job4j.multithreading.waitnotify;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    private static final Set<Integer> PRODUCED_ELEMENTS = new HashSet<>();
    private static final Set<Integer> CONSUMED_ELEMENTS = new HashSet<>();

    @Test
    public void whenProducedElementsToQueueEqualsConsumedElementsFromQueueResultIsTrue() throws InterruptedException {
        var queue = new SimpleBlockingQueue<Integer>(10);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                queue.offer(i);
                PRODUCED_ELEMENTS.add(i);
            }
        });
        Thread consumer = new Thread(() -> {
            int exit = 0;
            while (exit < 100) {
                CONSUMED_ELEMENTS.add(queue.poll());
                exit++;
            }
        });

        consumer.start();
        producer.start();
        consumer.join();
        producer.join();

        assertEquals(PRODUCED_ELEMENTS, CONSUMED_ELEMENTS);
    }
}