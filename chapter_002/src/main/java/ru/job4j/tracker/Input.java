package ru.job4j.tracker;

/**
 * Интерфейс ввода пользовательских данных из консоли.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.06.2019
 * @version 1.0
 */
public interface Input {
    String ask(String question);
}
