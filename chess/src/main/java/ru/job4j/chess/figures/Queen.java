package ru.job4j.chess.figures;

import ru.job4j.chess.FigureActions;

/**
 * Абстрактный класс реализует фигуру "Ферзь".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 05.07.2019
 */
public abstract class Queen implements Figure {
    final FigureActions figureActions = new FigureActions();

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] result = null;
        if (figureActions.isDiagonal(source, dest)) {
            result = figureActions.diagonalMove(source, dest);
        } else {
            result = figureActions.directMove(source, dest);
        }
        return result;
    }
}
