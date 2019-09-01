package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тест итератора для двухмерного массива int[][].
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 31.08.2019
 * @version 1.0
 */
public class MatrixIteratorTest {
    private Iterator<Integer> it1;
    private Iterator<Integer> it2;
    private Iterator<Integer> it3;
    private Iterator<Integer> it4;
    private Iterator<Integer> it5;
    private Iterator<Integer> it6;
    private Iterator<Integer> it7;

    @Before
    public void setUp() {
        it1 = new MatrixIterator(new int[][]{
                {1},
                {3, 4},
                {7}});
        it2 = new MatrixIterator(new int[][]{
                {1},
                {3, 4, 4},
                {7, 8, 1, 3}});

        it3 = new MatrixIterator(new int[][]{
                {},
                {3, 4, 4},
                {}});

        it4 = new MatrixIterator(new int[][]{
                {3, 4, 2, 5, 9},
                {3, 4, 4, 1},
                {4, 1, 3},
                {2, 1},
                {0}});

        it5 = new MatrixIterator(new int[][]{
                {2, 4},
                {},
                {2, 3}});

        it6 = new MatrixIterator(new int[][]{
                {2, 4},
                {},
                {},
                {},
                {},
                {},
                {2, 3}});

        it7 = new MatrixIterator(new int[][]{
                {2, 4},
                {},
                {3, 8},
                {},
                {},
                {1, 5, 6},
                {2, 3}});
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it1.next(), is(1));
        assertThat(it1.next(), is(3));
        assertThat(it1.next(), is(4));
        assertThat(it1.next(), is(7));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it1.hasNext(), is(true));
        assertThat(it1.hasNext(), is(true));
        assertThat(it1.next(), is(1));
        assertThat(it1.next(), is(3));
        assertThat(it1.next(), is(4));
        assertThat(it1.next(), is(7));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it1.hasNext(), is(true));
        assertThat(it1.next(), is(1));
        assertThat(it1.hasNext(), is(true));
        assertThat(it1.next(), is(3));
        assertThat(it1.hasNext(), is(true));
        assertThat(it1.next(), is(4));
        assertThat(it1.hasNext(), is(true));
        assertThat(it1.next(), is(7));
        assertThat(it1.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation2() {
        assertThat(it2.hasNext(), is(true));
        assertThat(it2.next(), is(1));
        assertThat(it2.hasNext(), is(true));
        assertThat(it2.next(), is(3));
        assertThat(it2.hasNext(), is(true));
        assertThat(it2.next(), is(4));
        assertThat(it2.hasNext(), is(true));
        assertThat(it2.next(), is(4));
        assertThat(it2.hasNext(), is(true));
        assertThat(it2.next(), is(7));
        assertThat(it2.hasNext(), is(true));
        assertThat(it2.next(), is(8));
        assertThat(it2.hasNext(), is(true));
        assertThat(it2.next(), is(1));
        assertThat(it2.hasNext(), is(true));
        assertThat(it2.next(), is(3));
        assertThat(it2.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation3() {
        assertThat(it3.hasNext(), is(true));
        assertThat(it3.next(), is(3));
        assertThat(it3.hasNext(), is(true));
        assertThat(it3.next(), is(4));
        assertThat(it3.hasNext(), is(true));
        assertThat(it3.next(), is(4));
        assertThat(it3.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation4() {
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(3));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(4));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(2));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(5));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(9));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(3));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(4));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(4));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(1));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(4));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(1));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(3));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(2));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(1));
        assertThat(it4.hasNext(), is(true));
        assertThat(it4.next(), is(0));
        assertThat(it4.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation5() {
        assertThat(it5.hasNext(), is(true));
        assertThat(it5.next(), is(2));
        assertThat(it5.hasNext(), is(true));
        assertThat(it5.next(), is(4));
        assertThat(it5.hasNext(), is(true));
        assertThat(it5.next(), is(2));
        assertThat(it5.hasNext(), is(true));
        assertThat(it5.next(), is(3));
        assertThat(it5.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation6() {
        assertThat(it6.hasNext(), is(true));
        assertThat(it6.next(), is(2));
        assertThat(it6.hasNext(), is(true));
        assertThat(it6.next(), is(4));
        assertThat(it6.hasNext(), is(true));
        assertThat(it6.next(), is(2));
        assertThat(it6.hasNext(), is(true));
        assertThat(it6.next(), is(3));
        assertThat(it6.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation7() {
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(2));
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(4));
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(3));
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(8));
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(1));
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(5));
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(6));
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(2));
        assertThat(it7.hasNext(), is(true));
        assertThat(it7.next(), is(3));
        assertThat(it7.hasNext(), is(false));
    }
}