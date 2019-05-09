package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SqAreaTest {

    @Test
    /**
     * Тест вычисления площади прямоугольника при известных значениях периметра и значения разницы между шириной и высотой.
     * @param p значение периметра прямоугольника = 4.
     * @param k - значение: ширина больше высоты в 1 раз.
     * @param is Ожидаемый результат 1.
     * @param assertThat Результат сравнения значения, полученного из метода square со значением 1.
     */
    public void p4k1s1() {
        assertThat(SqArea.square(4, 1), is(1));
    }

    @Test
    /**
     * Тест вычисления площади прямоугольника при известных значениях периметра и значения разницы между шириной и высотой.
     * @param p значение периметра прямоугольника = 6.
     * @param k - значение: ширина больше высоты в 2 раз.
     * @param is Ожидаемый результат 2.
     * @param assertThat Результат сравнения значения, полученного из метода square со значением 2.
     */
    public void p6k2s2() {
        assertThat(SqArea.square(6, 2), is(2));
    }
}