package ru.job4j.tracker;

import java.util.List;

/**
 * Интерфейс ввода пользовательских данных из консоли.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.06.2019
 * @version 2.0
 */
public interface Input {
    /**
     * Получение значения типа String, введенного в консоль пользователем.
     * @param question - запрос пользователю.
     * @return Значение типа String.
     */
    String ask(String question);

    /**
     * Получение значения типа int, введенного пользователем.
     * @param question - запрос пользователю.
     * @param range - List с диапазоном принимаемых значений от пользователя.
     * @return Значение типа int.
     */
    int ask(String question, List<Integer> range);
}
