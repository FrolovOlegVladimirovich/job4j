package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {

    @Test
    /**
     * Тест: поиск индекса массива со значением 5.
     * Ожидаемое значение идекса == 0.
     */
    public void whenArrayHas5Then0() {
        FindLoop find = new FindLoop();
        int[] data = {5, 10, 3};
        int value = 5;
        int result = find.indexOf(data, value);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    /**
     * Тест: поиск индекса массива со значением 5.
     * Ожидаемое значение идекса == -1 (индекс со значением 5 в заданном массиве отсутствует).
     */
    public void whenArrayHas5ThenNothingFind() {
        FindLoop find = new FindLoop();
        int[] data = {6, 10, 3};
        int value = 5;
        int result = find.indexOf(data, value);
        int expect = -1;
        assertThat(result, is(expect));
    }

    @Test
    /**
     * Тест: поиск индекса массива со значением 7.
     * Ожидаемое значение идекса == 6.
     */
    public void whenArrayHas7Then6() {
        FindLoop find = new FindLoop();
        int[] data = {6, 10, 3, 0, 8, 14, 7, 22, 0, 8};
        int value = 7;
        int result = find.indexOf(data, value);
        int expect = 6;
        assertThat(result, is(expect));
    }
}