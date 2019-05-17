package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {

    @Test
    public void whenSortArrayWithTenElementsThenSortArray1() {
        BubbleSort sort = new BubbleSort();
        int[] array = {7, 3, 5, 1, 10, 4, 8, 2, 9, 6};
        int[] result = sort.sort(array);
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortArrayWithTenElementsThenSortArray2() {
        BubbleSort sort = new BubbleSort();
        int[] array = {4, 3, 5, 1, 2};
        int[] result = sort.sort(array);
        int[] expect = {1, 2, 3, 4, 5};
        assertThat(result, is(expect));
    }
}