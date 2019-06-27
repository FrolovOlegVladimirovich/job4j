package ru.job4j.chess.figures;

import ru.job4j.chess.FigureActions;
import ru.job4j.chess.ImpossibleMoveException;

/**
 * Абстрактный класс реализует фигуру "Пешка".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 05.07.2019
 */
public abstract class Pawn implements Figure {
    private int sourceY = 0;
    private int deltaYOneStep = 0;
    private int deltaYTwoSteps = 0;

    /**
     * Метод проверяет ход фигуры.
     * Пешка может идти только вперед на одну клетку. Либо вперед на две клетки,
     * если это первый ход пешки.
     *
     * @param source - ячейка в которой находится фигура.
     * @param dest - ячейка в которую пойдет фигура.
     * @return пройденный путь фигуры в виде массива клеток.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (this.toString().equals("PawnWhite")) {
            sourceY = 1;
            deltaYTwoSteps = -2;
            deltaYOneStep = -1;
        } else if (this.toString().equals("PawnBlack")) {
            sourceY = 6;
            deltaYTwoSteps = 2;
            deltaYOneStep = 1;
        }
        FigureActions figureActions = new FigureActions();
        Cell[] steps = new Cell[Math.abs(dest.y - source.y)];
        if ((source.y == sourceY) && (dest.y + deltaYTwoSteps == source.y)) {
            steps[0] = figureActions.directMove(source, dest)[0];
            steps[1] = figureActions.directMove(source, dest)[1];
        } else if ((dest.y + deltaYOneStep == source.y)) {
            steps[0] = figureActions.directMove(source, dest)[0];
        } else {
            throw new ImpossibleMoveException("Данный ход невозможен!");
        }
        return steps;
    }
}
