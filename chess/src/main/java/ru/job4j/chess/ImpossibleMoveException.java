package ru.job4j.chess;

/**
 * Создает исключение-наследник RuntimeException: если ход фигуры невозможен.
 *
 *  * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 *  * @since 01.07.2019
 *  * @version 1.0
 */
public class ImpossibleMoveException extends RuntimeException {

    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
