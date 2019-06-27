package ru.job4j.chess.figures;

import ru.job4j.chess.FigureActions;

/**
 * Абстрактный класс реализует фигуру "Ладья".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 05.07.2019
 */
public abstract class Rook implements Figure {
    final FigureActions figureActions = new FigureActions();

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return figureActions.directMove(source, dest);
    }
}
