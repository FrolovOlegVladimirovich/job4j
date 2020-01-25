package ru.job4j.multithreading.threadpool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    public int taskAction() {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result++;
        }
        return result;
    }

    @Test
    public void whenNumOfTasksIsMoreThanTasksQueueSizeResultIsTrue() throws InterruptedException {
        AtomicInteger result = new AtomicInteger();
        Runnable task1 = () -> result.addAndGet(taskAction());
        Runnable task2 = () -> result.addAndGet(taskAction());
        Runnable task3 = () -> result.addAndGet(taskAction());
        Runnable task4 = () -> result.addAndGet(taskAction());
        Runnable task5 = () -> result.addAndGet(taskAction());
        Runnable task6 = () -> result.addAndGet(taskAction());
        ThreadPool threadPool = new ThreadPool(6);

        threadPool.work(task1);
        threadPool.work(task2);
        threadPool.work(task3);
        threadPool.work(task4);
        threadPool.work(task5);
        threadPool.work(task6);
        Thread.sleep(100);
        threadPool.shutdown();

        assertThat(result.get(), is(60));
    }

    @Test
    public void whenNumOfTasksEqualsTasksQueueSizeResultIsTrue() throws InterruptedException {
        AtomicInteger result = new AtomicInteger();
        Runnable task1 = () -> result.addAndGet(taskAction());
        Runnable task2 = () -> result.addAndGet(taskAction());
        Runnable task3 = () -> result.addAndGet(taskAction());
        ThreadPool threadPool = new ThreadPool(3);

        threadPool.work(task1);
        threadPool.work(task2);
        threadPool.work(task3);
        Thread.sleep(100);
        threadPool.shutdown();

        assertThat(result.get(), is(30));
    }
}