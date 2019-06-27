package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.QueenBlack;
import ru.job4j.chess.figures.white.QueenWhite;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест фигуры "Ферзь".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.07.2019
 * @version 1.0
 */
public class QueenTest {
    private final Logic logic = new Logic();

    /**
     * Перед стартом тестов добавляет необходимые фигуры на игровое поле.
     */
    @Before
    public void addFigures() {
        logic.add(new QueenBlack(Cell.C4));
        logic.add(new QueenWhite(Cell.D3));
    }

    /**
     * Проверяет ход фигуры в любом направлении по прямой, либо диагонали.
     */
    @Test
    public void rightMoves() {
        logic.move(Cell.C4, Cell.G8);
        logic.move(Cell.G8, Cell.G3);

        logic.move(Cell.D3, Cell.D1);
        logic.move(Cell.D1, Cell.A4);

        assertThat(logic.findBy(Cell.C4), is(-1));
        assertThat(logic.findBy(Cell.G3), is(0));
        assertThat(logic.findBy(Cell.D3), is(-1));
        assertThat(logic.findBy(Cell.A4), is(1));
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти не по правилам своей логики.
     */
    @Test
    public void wrongMoves() {
        try {
            logic.move(Cell.C4, Cell.E5);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C4), is(0));
            assertThat(logic.findBy(Cell.E5), is(-1));
        }
        try {
            logic.move(Cell.D3, Cell.F2);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.D3), is(1));
            assertThat(logic.findBy(Cell.F2), is(-1));
        }
    }

    /**
     * "На пути фигуры находится другая фигура!" - фигура пытается пойти через клетку с другой фигурой.
     */
    @Test
    public void moveThroughAnotherFigure() {
        try {
            logic.move(Cell.C4, Cell.E2);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.C4), is(0));
            assertThat(logic.findBy(Cell.E2), is(-1));
        }
        try {
            logic.move(Cell.D3, Cell.B5);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.D3), is(1));
            assertThat(logic.findBy(Cell.B5), is(-1));
        }
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти в клетку с другой фигурой.
     */
    @Test
    public void moveToAnotherFigure() {
        try {
            logic.move(Cell.C4, Cell.D3);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C4), is(0));
            assertThat(logic.findBy(Cell.D3), is(1));
        }
        try {
            logic.move(Cell.D3, Cell.C4);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.D3), is(1));
            assertThat(logic.findBy(Cell.C4), is(0));
        }
    }

    /**
     * "Фигура не найдена!" - фигура отсутствует.
     */
    @Test
    public void figureNotFound() {
        try {
            logic.move(Cell.H6, Cell.H8);
        } catch (FigureNotFoundException fnfe) {
            assertThat(logic.findBy(Cell.H6), is(-1));
            assertThat(logic.findBy(Cell.H8), is(-1));
        }
    }
}
