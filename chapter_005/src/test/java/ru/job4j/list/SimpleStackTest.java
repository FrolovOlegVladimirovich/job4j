package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {
    SimpleStack<Integer> stack = new SimpleStack<>();

    @Before
    public void setUp() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void whenPollAllElementsThenStackSizeShouldBe0() {
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        assertThat(stack.getSize(), is(0));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        stack.poll();
        stack.poll();
        stack.poll();
        stack.poll();
    }
}