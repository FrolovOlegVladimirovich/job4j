package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest  {
    @Test
    public void whenAddOnePlusOneThenTwo()  { // сложение
        Calculator calc = new Calculator();
        double result = calc.add(1D, 1D);
        double expected = 2D;
        assertThat(result, is(expected));
    }
    @Test
    public void whenDiv4On2Then2()  { // деление
        Calculator calc = new Calculator();
        double result = calc.div(4D, 2D);
        double expected = 2D;
        assertThat(result, is(expected));
    }
    @Test
    public void whenMult2On2Then4()  { // умножение
        Calculator calc = new Calculator();
        double result = calc.multiple(2D, 2D);
        double expected = 4D;
        assertThat(result, is(expected));
    }
    @Test
    public void whenSubt4from2Then2()  { // вычитание
        Calculator calc = new Calculator();
        double result = calc.subtract(4D, 2D);
        double expected = 2D;
        assertThat(result, is(expected));
    }
}