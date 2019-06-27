package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * Реализует логику движения фигур по шахматной доске.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 27.06.2019
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Метод реализует ход фигуры, если позволяет логика возможных движений конкретной фигуры.
     * Также проверяет наличие другой фигуры в клетке, на которую пользователь ставит свою фигуру.
     *
     * @param source - ячейка в которой находится фигура.
     * @param dest - ячейка в которую пойдет фигура.
     * @return true - если ход фигуры возможен.
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Фигура не найдена!");
        }
        Cell[] steps = this.figures[index].way(source, dest);
        if (!(steps.length > 0 && steps[steps.length - 1].equals(dest) && (this.findBy(dest) == -1))) {
            throw new ImpossibleMoveException("Данный ход невозможен!");
        }
        for (Cell i: steps) {
            if (findBy(i) != -1) {
                throw new OccupiedWayException("На пути фигуры находится другая фигура!");
            }
        }
        this.figures[index] = this.figures[index].copy(dest);
        return true;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    public int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
