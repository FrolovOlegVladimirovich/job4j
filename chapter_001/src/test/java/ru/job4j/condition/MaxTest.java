package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на программу вычисления максимального числа из двух.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 11.05.2019
 * @version 2.0
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

    @Test
    /**
     * Тест для трех чисел.
     *
     * Если три числа равны.
     * @param one первое число == 2.
     * @param two второе число == 2.
     * @param three третье число == 2.
     * @param result Результат вычисления максимального числа из one, two, three.
     * @param is Ожидаемый результат 2.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To2To2Then2() {
        Max max = new Max();
        int result = max.max(2, 2, 2);
        assertThat(result, is(2));
    }

    @Test
    /**
     * Тест для трех чисел.
     *
     * Если первое число максимальное.
     * @param one первое число == 4.
     * @param two второе число == 2.
     * @param three третье число == 2.
     * @param result Результат вычисления максимального числа из one, two, three.
     * @param is Ожидаемый результат 4.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax4To2To2Then4() {
        Max max = new Max();
        int result = max.max(4, 2, 2);
        assertThat(result, is(4));
    }

    @Test
    /**
     * Тест для трех чисел.
     *
     * Если второе число максимальное.
     * @param one первое число == 2.
     * @param two второе число == 4.
     * @param three третье число == 2.
     * @param result Результат вычисления максимального числа из one, two, three.
     * @param is Ожидаемый результат 4.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To4To2Then4() {
        Max max = new Max();
        int result = max.max(2, 4, 2);
        assertThat(result, is(4));
    }

    @Test
    /**
     * Тест для трех чисел.
     *
     * Если третье число максимальное.
     * @param one первое число == 2.
     * @param two второе число == 2.
     * @param three третье число == 4.
     * @param result Результат вычисления максимального числа из one, two, three.
     * @param is Ожидаемый результат 4.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To2To4Then4() {
        Max max = new Max();
        int result = max.max(2, 2, 4);
        assertThat(result, is(4));
    }

    @Test
    /**
     * Тест для четырех чисел.
     *
     * Если четыре числа равны.
     * @param one первое число == 2.
     * @param two второе число == 2.
     * @param three третье число == 2.
     * @param four четвертое число == 2.
     * @param result Результат вычисления максимального числа из one, two, three, four.
     * @param is Ожидаемый результат 2.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To2To2To2Then2() {
        Max max = new Max();
        int result = max.max(2, 2, 2, 2);
        assertThat(result, is(2));
    }

    @Test
    /**
     * Тест для четырех чисел.
     *
     * Если первое число максимальное.
     * @param one первое число == 4.
     * @param two второе число == 2.
     * @param three третье число == 2.
     * @param four четвертое число == 2.
     * @param result Результат вычисления максимального числа из one, two, three, four.
     * @param is Ожидаемый результат 4.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax4To2To2To2Then4() {
        Max max = new Max();
        int result = max.max(4, 2, 2, 2);
        assertThat(result, is(4));
    }

    @Test
    /**
     * Тест для четырех чисел.
     *
     * Если второе число максимальное.
     * @param one первое число == 2.
     * @param two второе число == 4.
     * @param three третье число == 2.
     * @param four четвертое число == 2.
     * @param result Результат вычисления максимального числа из one, two, three, four.
     * @param is Ожидаемый результат 4.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To4To2To2Then4() {
        Max max = new Max();
        int result = max.max(2, 4, 2, 2);
        assertThat(result, is(4));
    }

    @Test
    /**
     * Тест для четырех чисел.
     *
     * Если третье число максимальное.
     * @param one первое число == 2.
     * @param two второе число == 2.
     * @param three третье число == 4.
     * @param four четвертое число == 2.
     * @param result Результат вычисления максимального числа из one, two, three, four.
     * @param is Ожидаемый результат 4.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To2To4To2Then4() {
        Max max = new Max();
        int result = max.max(2, 2, 4, 2);
        assertThat(result, is(4));
    }

    @Test
    /**
     * Тест для четырех чисел.
     *
     * Если четвертое число максимальное.
     * @param one первое число == 2.
     * @param two второе число == 2.
     * @param three третье число == 2.
     * @param four четвертое число == 4.
     * @param result Результат вычисления максимального числа из one, two, three, four.
     * @param is Ожидаемый результат 4.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenMax2To2To2To4Then4() {
        Max max = new Max();
        int result = max.max(2, 2, 4, 4);
        assertThat(result, is(4));
    }
}