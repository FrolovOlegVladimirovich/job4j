package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    private SimpleArray<Integer> intCollection = new SimpleArray<>(9);
    private SimpleArray<String> stringCollection = new SimpleArray<>(6);

    @Before
    public void addElements() {
        intCollection.add(1);
        intCollection.add(2);
        intCollection.add(3);
        intCollection.add(4);
        intCollection.add(5);
        intCollection.add(6);
        intCollection.add(7);
        intCollection.add(8);
        intCollection.add(9);

        stringCollection.add("Hello");
        stringCollection.add("world");
        stringCollection.add("My");
        stringCollection.add("name");
        stringCollection.add("is");
        stringCollection.add("Oleg");
    }

    @Test
    public void testAddAndSet() {
        intCollection.set(5, 100);
        stringCollection.set(1, "world!");
        stringCollection.set(5, "Oleg.");

        Integer[] intExpect = new Integer[] {1, 2, 3, 4, 5, 100, 7, 8, 9};
        String[] stringExpect = new String[] {"Hello", "world!", "My", "name", "is", "Oleg."};
        Integer[] intResult = new Integer[9];
        String[] stringResult = new String[6];

        Iterator intIt = intCollection.iterator();
        while (intIt.hasNext()) {
            for (int i = 0; i != intResult.length; i++) {
                intResult[i] = (Integer) intIt.next();
            }
        }

        Iterator stringIt = stringCollection.iterator();
        while (stringIt.hasNext()) {
            for (int i = 0; i != stringResult.length; i++) {
                stringResult[i] = (String) stringIt.next();
            }
        }

        assertThat(intResult, is(intExpect));
        assertThat(stringResult, is(stringExpect));
    }

    @Test (expected = RuntimeException.class)
    public void testAddException() {
        intCollection.add(10);
    }

    @Test (expected = NoSuchElementException.class)
    public void testSetException() {
        intCollection.set(10, 100);
    }

    @Test
    public void testGet() {
        Integer intResult = intCollection.get(4);
        Integer intExpect = 5;

        String  stringResult = stringCollection.get(1);
        String  stringExpect = "world";

        assertThat(intResult, is(intExpect));
        assertThat(stringResult, is(stringExpect));
    }

    @Test (expected = NoSuchElementException.class)
    public void testGetException() {
        intCollection.get(10);
    }

    @Test
    public void testRemove() {
        intCollection.remove(3);
        intCollection.remove(6);
        stringCollection.remove(1);

        Integer[] intExpect = new Integer[] {1, 2, 3, 5, 6, 7, 9};
        Integer[] intResult = new Integer[7];
        String[] stringExpect = new String[] {"Hello", "My", "name", "is", "Oleg"};
        String[] stringResult = new String[5];

        Iterator intIt = intCollection.iterator();
        while (intIt.hasNext()) {
            for (int i = 0; i != intResult.length; i++) {
                intResult[i] = (Integer) intIt.next();
            }
        }

        Iterator stringIt = stringCollection.iterator();
        while (stringIt.hasNext()) {
            for (int i = 0; i != stringResult.length; i++) {
                stringResult[i] = (String) stringIt.next();
            }
        }

        assertThat(intResult, is(intExpect));
        assertThat(stringResult, is(stringExpect));
    }

    @Test (expected = NoSuchElementException.class)
    public void testRemoveException() {
        intCollection.remove(3);
        intCollection.remove(6);
        intCollection.remove(7);
    }

    @Test
    public void testIterator() {
        SimpleArray<Integer> result = new SimpleArray<>(9);
        Iterator it = intCollection.iterator();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }

        assertThat(result, is(intCollection));
    }

    @Test (expected = NoSuchElementException.class)
    public void testIteratorException() {
        SimpleArray<Integer> result = new SimpleArray<>(9);
        Iterator it = intCollection.iterator();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }

        it.next();
    }
}