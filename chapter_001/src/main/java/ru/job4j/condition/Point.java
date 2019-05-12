package ru.job4j.condition;

/**
 * Вычисление расстояния между точками в системе координат.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.05.2019
 * @version 1.0
 */
public class Point {

    /**
     * Метод для вычисление расстояния между точками в системе координат.
     *
     * Math.pow(a, b) - возведение числа а в степень b.
     * Math.sqrt(a) - извлечение квадратного корня.
     *
     * @param x1 кооридината точки 1 по оси x.
     * @param x2 кооридината точки 2 по оси x.
     * @param y1 кооридината точки 1 по оси y.
     * @param y2 кооридината точки 2 по оси y.
     * @return Расстояние между точками 1 и 2 в система координат xy.
     */
    public double distance(int x1, int y1, int x2, int y2) {
        double first = Math.pow(x2 - x1, 2);
        double second = Math.pow(y2 - y1, 2);
        return Math.sqrt(first + second);
    }
}