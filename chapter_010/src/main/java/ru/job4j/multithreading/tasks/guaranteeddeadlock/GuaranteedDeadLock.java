package ru.job4j.multithreading.tasks.guaranteeddeadlock;

import java.util.concurrent.CountDownLatch;

/**
 * Guaranteed deadlock with CountDownLatch without using sleep().
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class GuaranteedDeadLock {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cd = new CountDownLatch(2);

        Thread th1 = new Thread(
                () -> {
                    cd.countDown();
                    try {
                        cd.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread th2 = new Thread(
                () -> {
                    try {
                        cd.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        th1.start();
        th2.start();
        th1.join();
        th2.join();
    }
}