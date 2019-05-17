package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckTest {
    @Test
    /**
     * Если все значения массива true (четное количество элементов).
     */
    public void whenDataMonoTrueEvenResultTrue() {
        Check check = new Check();
        boolean[] input = {true, true, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    /**
     * Если все значения массива true (НЕчетное количество элементов).
     */
    public void whenDataMonoTrueNotEvenResultTrue() {
        Check check = new Check();
        boolean[] input = {true, true, true, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    /**
     * Если все значения массива false (четное количество элементов).
     */
    public void whenDataMonoFalseEvenResultTrue() {
        Check check = new Check();
        boolean[] input = {false, false, false, false};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    /**
     * Если все значения массива false (Нечетное количество элементов).
     */
    public void whenDataMonoFalseNotEvenResultTrue() {
        Check check = new Check();
        boolean[] input = {false, false, false, false, false};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    /**
     * Если все значения массива не одинаковы (четное количество элементов).
     */
    public void whenDataMonoFalseAndTrueEvenResultFalse() {
        Check check = new Check();
        boolean[] input = {false, true, false, true};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

    @Test
    /**
     * Если все значения массива не одинаковы (НЕчетное количество элементов).
     */
    public void whenDataMonoFalseAndTrueNotEvenResultFalse() {
        Check check = new Check();
        boolean[] input = {false, true, false, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}