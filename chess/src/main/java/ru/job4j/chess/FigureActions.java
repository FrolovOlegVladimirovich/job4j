package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;

/**
 * Реализует общие действия для использования всеми типами фигур.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 01.07.2019
 */
public class FigureActions {
    private int deltaX;
    private int deltaY;

    /**
     * Вычисляет в какую из сторон пойдет фигура.
     * @param source - ячейка в которой находится фигура.
     * @param dest - ячейка в которую пойдет фигура.
     */
    public void setDeltas(Cell source, Cell dest) {
        if (dest.x > source.x && dest.y < source.y) {
            this.deltaX = +1;
            this.deltaY = -1;
        } else if (dest.x < source.x && dest.y < source.y) {
            this.deltaX = -1;
            this.deltaY = -1;
        } else if (dest.x > source.x && dest.y > source.y) {
            this.deltaX = +1;
            this.deltaY = +1;
        } else if (dest.x < source.x && dest.y > source.y) {
            this.deltaX = -1;
            this.deltaY = +1;
        } else if (dest.x == source.x && dest.y < source.y) {
            this.deltaX = 0;
            this.deltaY = -1;
        } else if (dest.x < source.x && dest.y == source.y) {
            this.deltaX = -1;
            this.deltaY = 0;
        } else if (dest.x == source.x && dest.y > source.y) {
            this.deltaX = 0;
            this.deltaY = +1;
        } else if (dest.x > source.x && dest.y == source.y) {
            this.deltaX = +1;
            this.deltaY = 0;
        }
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    /**
     * Проверяет - ходит ли фигура по диагонали.
     *
     * @param source - ячейка в которой находится фигура.
     * @param dest - ячейка в которую пойдет фигура.
     * @return true - если фигура ходит по диагонали.
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        boolean result = false;
        if (Math.abs(dest.x - source.x) == Math.abs(dest.y - source.y)) {
            result = true;
        }
        return result;
    }

    /**
     * Реализует ход фигуры по диагонали.
     *
     * @param source - ячейка в которой находится фигура.
     * @param dest - ячейка в которую пойдет фигура.
     * @return пройденный путь фигуры в виде массива клеток.
     */
    public Cell[] diagonalMove(Cell source, Cell dest) {
        setDeltas(source, dest);
        Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Данный ход невозможен!");
        }
        int variableX = source.x;
        int variableY = source.y;
        for (int i = 0; i != steps.length; i++) {
            for (Cell index: Cell.values()) {
                if (index.x == variableX + getDeltaX() && index.y == variableY + getDeltaY()) {
                    steps[i] = index;
                    variableX = index.x;
                    variableY = index.y;
                    break;
                }
            }
        }
        return steps;
    }

    /**
     * Реализует ход фигуры по вертикали/горизонтали.
     *
     * @param source - ячейка в которой находится фигура.
     * @param dest - ячейка в которую пойдет фигура.
     * @return пройденный путь фигуры в виде массива клеток.
     */
    public Cell[] directMove(Cell source, Cell dest) {
        int variableY = source.y;
        int variableX = source.x;
        int cells = 0;
        setDeltas(source, dest);
        if (getDeltaX() == 0) {
            cells = Math.abs(dest.y - source.y);
        } else if (getDeltaY() == 0)  {
            cells = Math.abs(dest.x - source.x);
        }
        Cell[] steps = new Cell[cells];
        if (!(source.x == dest.x || source.y == dest.y)) {
            throw new ImpossibleMoveException("Данный ход невозможен!");
        }
        for (int i = 0; i != steps.length; i++) {
            for (Cell index : Cell.values()) {
                if (index.y == variableY + getDeltaY() && index.x == variableX + getDeltaX()) {
                    steps[i] = index;
                    variableY = index.y;
                    variableX = index.x;
                    break;
                }

            }
        }
        return steps;
    }
}
