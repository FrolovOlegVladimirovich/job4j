package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest  {

    @Test
    /**
     * Тест сложения.
     * @param 1D Число 1 типа double
     * @param 2D Число 2 типа double
     * @param result Результат сложения числа 1 и числа 2 при использовании метода add
     * @param expected Ожидаемый результат сложения числа 1 и числа 2
     * @param assertThat Результат сравнения result и expected
     */
    public void whenAddOnePlusOneThenTwo()  {
        Calculator calc = new Calculator();
        double result = calc.add(1D, 2D);
        double expected = 3D;
        assertThat(result, is(expected));
    }

    @Test
    /**
     * Тест деления.
     * @param 6D Число 6 типа double
     * @param 3D Число 3 типа double
     * @param result Результат деления числа 6 на число 3 при использовании метода div
     * @param expected Ожидаемый результат деления числа 6 на число 3
     * @param assertThat Результат сравнения result и expected
     */
    public void whenDiv4On2Then2()  {
        Calculator calc = new Calculator();
        double result = calc.div(6D, 3D);
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    /**
     * Тест умножения.
     * @param 2D Число 2 типа double
     * @param 3D Число 3 типа double
     * @param result Результат умножения числа 2 на число 3 при использовании метода multiple
     * @param expected Ожидаемый результат умножения числа 2 на число 3
     * @param assertThat Результат сравнения result и expected
     */
    public void whenMult2On2Then4()  {
        Calculator calc = new Calculator();
        double result = calc.multiple(2D, 3D);
        double expected = 6D;
        assertThat(result, is(expected));
    }

    @Test
    /**
     * Тест вычитания.
     * @param 4D Число 4 типа double
     * @param 2D Число 2 типа double
     * @param result Результат вычитания числа 4 из числа 2 при использовании метода subtract
     * @param expected Ожидаемый результат вычитания числа 4 из числа 2
     * @param assertThat Результат сравнения result и expected
     */
    public void whenSubt4from2Then2()  {
        Calculator calc = new Calculator();
        double result = calc.subtract(4D, 2D);
        double expected = 2D;
        assertThat(result, is(expected));
    }
}