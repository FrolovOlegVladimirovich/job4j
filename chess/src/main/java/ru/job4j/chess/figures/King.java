package ru.job4j.chess.figures;

import ru.job4j.chess.FigureActions;

/**
 * Абстрактный класс реализует фигуру "Король".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 05.07.2019
 */
public abstract class King implements Figure {
    final FigureActions figureActions = new FigureActions();

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] result = new Cell[1];
        if (figureActions.isDiagonal(source, dest)) {
            result[0] = figureActions.diagonalMove(source, dest)[0];
        } else {
            result[0] = figureActions.directMove(source, dest)[0];
        }
        return result;
    }
}
