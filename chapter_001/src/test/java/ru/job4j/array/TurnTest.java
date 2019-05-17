package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {

    @Test
    /**
     * Если массив из четного количества элементов.
     */
    public void when12345678910Result10987654321() {
        Turn turn = new Turn();
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = turn.back(data);
        int[] expect = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertThat(result, is(expect));
    }

    /**
     * Если массив из четного количества элементов.
     */
    @Test
    public void when123456Result654321() {
        Turn turn = new Turn();
        int[] data = {1, 2, 3, 4, 5, 6};
        int[] result = turn.back(data);
        int[] expect = {6, 5, 4, 3, 2, 1};
        assertThat(result, is(expect));
    }

    /**
     * Если массив из НЕчетного количества элементов.
     */
    @Test
    public void when12345Result54321() {
        Turn turn = new Turn();
        int[] data = {1, 2, 3, 4, 5};
        int[] result = turn.back(data);
        int[] expect = {5, 4, 3, 2, 1};
        assertThat(result, is(expect));
    }

    /**
     * Если массив из НЕчетного количества элементов.
     */
    @Test
    public void when123456789Result987654321() {
        Turn turn = new Turn();
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = turn.back(data);
        int[] expect = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertThat(result, is(expect));
    }
}
