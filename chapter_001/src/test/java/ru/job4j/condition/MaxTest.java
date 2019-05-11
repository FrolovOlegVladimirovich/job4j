package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на программу вычисления максимального числа из двух.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 11.05.2019
 * @version 1.0
 */
public class MaxTest {

    @Test
    /**
     * Если первое число максимальное.
     * @param left первое число == 2.
     * @param right второе число == 1.
     * @param result Результат вычисления максимального числа из left и right.
     * @param is Ожидаемый результат 2.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To1Then2() {
        Max max = new Max();
        int result = max.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    /**
     * Если второе число максимальное.
     * @param left первое число == 1.
     * @param right второе число == 2.
     * @param result Результат вычисления максимального числа из left и right.
     * @param is Ожидаемый результат 2.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax1To2Then2() {
        Max max = new Max();
        int result = max.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    /**
     * Если оба числа равны.
     * @param left первое число == 2.
     * @param right второе число == 2.
     * @param result Результат вычисления максимального числа из left и right.
     * @param is Ожидаемый результат 2.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To2Then2() {
        Max max = new Max();
        int result = max.max(2, 2);
        assertThat(result, is(2));
    }
}