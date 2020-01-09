package ru.job4j.multithreading.waitnotify;

/**
 * Demonstration of the consumer stop mechanism, where the manufacturer has finished its work.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }

        );
        producer.start();
        consumer.start();
        producer.join();
        while (!queue.isEmpty()) {
            Thread.sleep(100);
        }
        consumer.interrupt();
    }
}