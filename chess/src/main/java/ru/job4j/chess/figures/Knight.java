package ru.job4j.chess.figures;

import ru.job4j.chess.ImpossibleMoveException;

/**
 * Абстрактный класс реализует фигуру "Конь".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 05.07.2019
 */
public abstract class Knight implements Figure {

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!((Math.abs(dest.x - source.x) == 1 && Math.abs(dest.y - source.y) == 2)
                ||
                (Math.abs(dest.x - source.x) == 2 && Math.abs(dest.y - source.y) == 1))) {
            throw new ImpossibleMoveException("Данный ход невозможен!");
        }
        return new Cell[]{dest};
    }
}
