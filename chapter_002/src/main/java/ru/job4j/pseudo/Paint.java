package ru.job4j.pseudo;

/**
 * Доступ к интерфейсу Shape и его реализациям.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.06.2019
 * @version 1.0
 */
public class Paint {

    /**
     * Вывод фигуры в консоль.
     *
     * @param shape - тип фигуры.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

}
