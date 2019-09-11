package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {
    SimpleSet<Integer> set = new SimpleSet<>();
    Iterator<Integer> it;

    @Before
    public void setUp() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(2);
        set.add(4);
        set.add(1);

        it  = set.iterator();
    }

    @Test
    public void whenAdd123241ResultShouldBe1234() {
        Integer[] result = new Integer[4];
        Integer[] expect = {1, 2, 3, 4};

        int i = 0;
        while (it.hasNext() && i != result.length) {
            result[i++] = it.next();
        }

        assertThat(result, is(expect));
        assertThat(set.size(), is(4));
    }

    @Test
    public void whenAdd1Null21ResultShouldBe1Null2() {
        SimpleSet<Integer> set = new SimpleSet<>();
        Iterator<Integer> it;

        set.add(1);
        set.add(null);
        set.add(2);
        set.add(1);

        it  = set.iterator();

        Integer[] result = new Integer[3];
        Integer[] expect = {1, null, 2};

        int i = 0;
        while (it.hasNext() && i != result.length) {
            result[i++] = it.next();
        }

        assertThat(result, is(expect));
        assertThat(set.size(), is(3));
    }

    @Test
    public void whenAddNull2Null2ResultShouldBeNull2() {
        SimpleSet<Integer> set = new SimpleSet<>();
        Iterator<Integer> it;

        set.add(null);
        set.add(2);
        set.add(null);
        set.add(2);

        it = set.iterator();

        Integer[] result = new Integer[2];
        Integer[] expect = {null, 2};

        int i = 0;
        while (it.hasNext() && i != result.length) {
            result[i++] = it.next();
        }


        assertThat(set.size(), is(2));
        assertThat(result, is(expect));
    }

    @Test
    public void whenAddAllNullsResultShouldBeSize1() {
        SimpleSet<Integer> set = new SimpleSet<>();

        set.add(null);
        set.add(null);
        set.add(null);

        assertThat(set.size(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        it.next();
        set.add(10);
        it.next();
    }

    @Test
    public void whenNoElementsResultShouldBeFalse() {
        SimpleSet<Integer> set = new SimpleSet<>();
        Iterator it = set.iterator();
        assertThat(it.hasNext(), is(false));
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
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }
}