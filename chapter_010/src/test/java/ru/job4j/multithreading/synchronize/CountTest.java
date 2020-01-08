package ru.job4j.multithreading.synchronize;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CountTest {

    /**
     * This class describes a thread with a counter..
     */
    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //Create a counter.
        final Count count = new Count();
        //Create threads.
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        //Run the threads.
        first.start();
        second.start();
        //Causes the main thread wait for our threads to complete.
        first.join();
        second.join();
        //Check the result.
        assertThat(count.get(), is(2));
    }
}