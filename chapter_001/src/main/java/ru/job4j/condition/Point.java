package ru.job4j.condition;

/**
 * Вычисление расстояния между точками в системе координат.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.05.2019
 * @version 2.0
 */
public class Point {
    private int x;
    private int y;

    /**
     * Конструктор класса Point.
     * Присваивает координаты точке.
     *
     * @param first координата точки по оси x.
     * @param first координата точки по оси y.
     */
    public Point (int first, int second) {
        this.x = first;
        this.y = second;
    }

    /**
     * Метод для вычисление расстояния между двумя точками в системе координат xy.
     *
     * Math.pow(a, b) - возведение числа а в степень b.
     * Math.sqrt(a) - извлечение квадратного корня.
     *
     * @param that координаты точки 2.
     * @return Расстояние между точками 1 и 2 в системе координат xy.
     */
    public double distance(Point that) {
        double first = Math.pow(this.x - that.x, 2);
        double second = Math.pow(this.y - that.y, 2);
        return Math.sqrt(first + second);
    }

    public void info() {
        System.out.println(String.format("Point [%s, %s]", this.x, this.y));
    }
}