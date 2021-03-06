package ru.job4j.multithreading.waitnotify;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducedElementsToQueueEqualsConsumedElementsFromQueueResultIsTrue() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        num ->  {
                            try {
                                queue.offer(num);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                )
        );
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();

        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}