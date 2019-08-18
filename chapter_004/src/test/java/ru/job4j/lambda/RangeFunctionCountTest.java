package ru.job4j.lambda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест расчетов функции в диапазоне.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class RangeFunctionCountTest {

    /**
     * Расчет линейной функции.
     */
    @Test
    public void whenLinearFunctionThenLinearResults() {
        RangeFunctionCount function = new RangeFunctionCount();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    /**
     * Расчет квадратичной функции.
     */
    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        RangeFunctionCount function = new RangeFunctionCount();
        List<Double> result = function.diapason(5, 8, x -> (3 * Math.pow(x, 2)) + (2 * x) + 1);
        List<Double> expected = Arrays.asList(86D, 121D, 162D);
        assertThat(result, is(expected));
    }

    /**
     * Расчет логарифмической функции.
     */
    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        RangeFunctionCount function = new RangeFunctionCount();
        List<Double> result = function.diapason(5, 8, x -> (double) Math.round(Math.log(x) * 1000) / 1000);
        List<Double> expected = Arrays.asList(1.609D, 1.792D, 1.946D);
        assertThat(result, is(expected));
    }
}