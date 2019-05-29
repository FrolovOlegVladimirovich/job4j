package ru.job4j.condition;

/**
 * Вычисление расстояния между точками в системе координат.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.05.2019
 * @version 3.0
 */
public class Point {
    private int x;
    private int y;
    private int z;

    /**
     * Конструктор класса Point.
     * Присваивает координаты точке.
     *
     * @param first координата точки по оси x.
     * @param second координата точки по оси y.
     */
    public Point (int first, int second) {
        this.x = first;
        this.y = second;
    }

    /**
     * Перегружаем конструктор класса Point на прием трех координат x y z.
     * Присваивает координаты точке в трехмерном пространстве.
     *
     * @param x координата точки по оси x.
     * @param y координата точки по оси y.
     * @param z координата точки по оси z.
     */
    public Point (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    /**
     * Метод для вычисление расстояния между двумя точками в системе координат xyz.
     *
     * Math.pow(a, b) - возведение числа а в степень b.
     * Math.sqrt(a) - извлечение квадратного корня.
     *
     * @param that координаты точки 2.
     * @return Расстояние между точками 1 и 2 в системе координат xyz.
     */
    public double distance3d (Point that) {
        return Math.sqrt(Math.pow(that.x - this.x, 2) + Math.pow(that.y - this.y, 2) + Math.pow(that.z - this.z, 2));
    }

    public void info() {
        System.out.println(String.format("Point [%s, %s]", this.x, this.y));
    }
}