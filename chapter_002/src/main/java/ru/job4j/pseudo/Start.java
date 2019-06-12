package ru.job4j.pseudo;

/**
 * Запуск программы для вывода в консоль фигуры треугольник и квадрат.
 * Реализуется паттерн "Стратегия".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.06.2019
 * @version 1.0
 */
public class Start {
    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        paint.draw(new Square());
    }
}
