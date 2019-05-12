package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на программу вычисления максимального числа из трех.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 12.05.2019
 * @version 1.0
 */
public class MultiMaxTest {

    @Test
    /**
     * Если первое число максимальное.
     *
     * @param is Ожидаемый результат 7.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenFirstMax() {
        MultiMax check = new MultiMax();
        int result = check.max(7, 3, 6);
        assertThat(result, is(7));
    }

    @Test
    /**
     * Если второе число максимальное.
     *
     * @param is Ожидаемый результат 4.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenSecondMax() {
        MultiMax check = new MultiMax();
        int result = check.max(1, 4, 2);
        assertThat(result, is(4));
    }

    @Test
    /**
     * Если третье число максимальное.
     *
     * @param is Ожидаемый результат 5.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenThirdMax() {
        MultiMax check = new MultiMax();
        int result = check.max(4, 2, 5);
        assertThat(result, is(5));
    }

    @Test
    /**
     * Если все числа одинаковые.
     *
     * @param is Ожидаемый результат 3.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenAllTheSame() {
        MultiMax check = new MultiMax();
        int result = check.max(3, 3, 3);
        assertThat(result, is(3));
    }
}