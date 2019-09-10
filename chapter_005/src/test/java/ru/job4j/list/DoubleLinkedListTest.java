package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DoubleLinkedListTest {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
    Iterator<Integer> it;

    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        it = list.iterator();
    }

    @Test
    public void whenAdd5ElementsThenListContains5Elements() {
        assertThat(list.getSize(), is(5));
    }

    @Test
    public void whenAdd5ElementsThenGetThis5ElementsByIndex() {
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(4));
        assertThat(list.get(4), is(5));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBoundsException() {
        list.get(6);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        it.next();
        list.add(10);
        it.next();
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
    }
}