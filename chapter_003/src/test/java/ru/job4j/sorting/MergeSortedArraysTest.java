package ru.job4j.sorting;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты объединения двух отсортированных массивов в один.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.08.2019
 * @version 1.0
 */
public class MergeSortedArraysTest {

    @Test
    public void mergeTest1() {
        int[] left = {1, 3};
        int[] right = {2, 4};
        int[] result = {1, 2, 3, 4};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }

    @Test
    public void mergeTest2() {
        int[] left = {1, 3, 5, 7};
        int[] right = {2, 4};
        int[] result = {1, 2, 3, 4, 5, 7};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }

    @Test
    public void mergeTest3() {
        int[] left = {2, 4};
        int[] right = {1, 3, 5, 7};
        int[] result = {1, 2, 3, 4, 5, 7};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }

    @Test
    public void mergeTest4() {
        int[] left = {7, 17, 20, 50, 64, 65};
        int[] right = {8, 10, 11, 63};
        int[] result = {7, 8, 10, 11, 17, 20, 50, 63, 64, 65};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }


    @Test
    public void mergeTest5() {
        int[] left = {3, 5, 6, 7, 8, 9, 12};
        int[] right = {1, 2, 4, 8, 9, 11, 13};
        int[] result = {1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 9, 11, 12, 13};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }

    @Test
    public void mergeTest6() {
        int[] left = {3, 5, 6, 7, 8, 9, 11, 12, 14, 18};
        int[] right = {1, 2, 4, 8, 9, 11, 13};
        int[] result = {1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 9, 11, 11, 12, 13, 14, 18};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }

    @Test
    public void mergeTest7() {
        int[] left = {1, 2, 4, 8, 9, 11, 13};
        int[] right = {3, 5, 6, 7, 8, 9, 11, 12, 14, 18};
        int[] result = {1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 9, 11, 11, 12, 13, 14, 18};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }

    @Test
    public void mergeTest8() {
        int[] left = {1, 2, 4, 8, 9, 11, 13};
        int[] right = {7};
        int[] result = {1, 2, 4, 7, 8, 9, 11, 13};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }

    @Test
    public void mergeTest9() {
        int[] left = {8};
        int[] right = {1, 2, 4, 8, 9, 11, 13};
        int[] result = {1, 2, 4, 8, 8, 9, 11, 13};
        MergeSortedArrays test = new MergeSortedArrays();
        assertThat(test.merge(left, right), is(result));
    }
}
