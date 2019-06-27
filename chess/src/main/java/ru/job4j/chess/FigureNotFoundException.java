package ru.job4j.chess;

/**
 * Создает исключение-наследник RuntimeException: фигура не найдена.
 *
 *  * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 *  * @since 01.07.2019
 *  * @version 1.0
 */
public class FigureNotFoundException extends RuntimeException {

    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
