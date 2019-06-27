package ru.job4j.chess;

/**
 * Создает исключение-наследник RuntimeException: если путь фигуры преграждают другие фигуры.
 *
 *  * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 *  * @since 01.07.2019
 *  * @version 1.0
 */
public class OccupiedWayException extends RuntimeException {

    public OccupiedWayException(String msg) {
        super(msg);
    }
}
