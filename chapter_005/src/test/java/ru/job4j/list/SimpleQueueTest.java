package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenPollAtOnceAfterPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        assertThat(queue.poll(), is(1));
        queue.push(2);
        assertThat(queue.poll(), is(2));
        queue.push(3);
        assertThat(queue.poll(), is(3));
    }

    @Test
    public void whenPollAllElementsAfterPushingAllElements() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }

    @Test
    public void whenPushAndPollElementsInADifferentOrder() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        assertThat(queue.poll(), is(1));
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        queue.poll();
        queue.push(3);
        queue.poll();
        queue.poll();
    }
}