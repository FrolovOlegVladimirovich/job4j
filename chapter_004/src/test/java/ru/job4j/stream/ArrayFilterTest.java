package ru.job4j.stream;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса ArrayFilter.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 30.08.2019
 * @version 1.0
 */
public class ArrayFilterTest {

    @Test
    public void whenResultShouldBe36() {
        ArrayFilter filter = new ArrayFilter();
        int result = filter.evenSquaredSum(new int[]{4, 5, 2, 4});
        assertThat(result, is(36));
    }

    @Test
    public void whenResultShouldBe56() {
        ArrayFilter filter = new ArrayFilter();
        int result = filter.evenSquaredSum(new int[]{1, 2, 3, 4, 5, 6});
        assertThat(result, is(56));
    }

    @Test
    public void whenArrayValuesAllOddResultShouldBeZero() {
        ArrayFilter filter = new ArrayFilter();
        int result = filter.evenSquaredSum(new int[]{7, 5, 3, 9, 11, 3});
        assertThat(result, is(0));
    }

    @Test
    public void whenArrayIsEmptyResultShouldBeZero() {
        ArrayFilter filter = new ArrayFilter();
        int result = filter.evenSquaredSum(new int[]{});
        assertThat(result, is(0));
    }
}