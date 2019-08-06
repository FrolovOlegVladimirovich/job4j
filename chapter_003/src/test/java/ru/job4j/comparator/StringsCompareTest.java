package ru.job4j.comparator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Тест компаратора для строк StringsCompare.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.08.2019
 * @version 1.0
 */
public class StringsCompareTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare("Ivanov", "Ivanov");
        assertThat(rst, is(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare("Ivanov", "Ivanovа");
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftLengthGreaterThanRightResultShouldBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare("Ivanova", "Ivanov");
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare("Petrov", "Ivanova");
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare("Petrov", "Patrov");
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare("Patrova", "Petrov");
        assertThat(rst, lessThan(0));
    }
}
