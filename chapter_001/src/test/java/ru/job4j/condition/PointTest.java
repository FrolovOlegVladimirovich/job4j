package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PointTest {

    @Test
    /**
     * Тест вычисления расстояния между точками в системе координат.
     * @param x1 кооридината точки 1 по оси x равна 0.
     * @param y1 кооридината точки 1 по оси y равна 0.
     * @param x2 кооридината точки 2 по оси x равна 0.
     * @param y2 кооридината точки 2 по оси y равна 10.
     * @param result Результат вычисления расстояния между точками в системе координат при использовании метода distance.
     * @param is Ожидаемый результат 10.
     * @param assertThat Результат сравнения result и is.
     */
    public void whenZeroAndTenThenTen() {
        Point point = new Point();
        double result = point.distance(0, 0, 0, 10);
        assertThat(result, is(10D));
    }
}