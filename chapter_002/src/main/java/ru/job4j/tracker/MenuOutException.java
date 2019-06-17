package ru.job4j.tracker;

/**
 * Создает исключение-наследник RuntimeException.
 *
 *  * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 *  * @since 17.06.2019
 *  * @version 1.0
 */
public class MenuOutException extends RuntimeException {

    /**
     * Исключение выводит в консоль заданное сообщение в формате String.
     * @param msg - сообщение в формате String.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
