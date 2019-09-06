package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayListTest {
    SimpleArrayList<Integer> list = new SimpleArrayList<>();

    @Before
    public void beforeTest() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAdd4ThenListContains1234() {
        list.add(4);
        Integer[] expected = new Integer[] {1, 2, 3, 4};
        Integer[] result = new Integer[4];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        assertThat(result, is(expected));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        Iterator it = list.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        Iterator iterator = list.iterator();
        iterator.next();
        list.add(10);
        iterator.next();
    }

    @Test
    public void whenNoElementsResultShouldBeFalse() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        Iterator it = list.iterator();
        assertThat(it.hasNext(), is(false));
    }
}