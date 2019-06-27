package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.KnightBlack;
import ru.job4j.chess.figures.black.PawnBlack;
import ru.job4j.chess.figures.white.KnightWhite;
import ru.job4j.chess.figures.white.PawnWhite;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест фигуры "Конь".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.07.2019
 * @version 1.0
 */
public class KnightTest {
    private final Logic logic = new Logic();

    /**
     * Перед стартом тестов добавляет необходимые фигуры на игровое поле.
     */
    @Before
    public void addFigures() {
        logic.add(new KnightBlack(Cell.C5));
        logic.add(new KnightWhite(Cell.D3));
        logic.add(new PawnWhite(Cell.D5));
        logic.add(new PawnBlack(Cell.B3));
    }

    /**
     * Проверяет ход фигуры правильном направлении.
     */
    @Test
    public void rightMoves() {
        logic.move(Cell.D3, Cell.F4);
        logic.move(Cell.C5, Cell.D7);

        assertThat(logic.findBy(Cell.D3), is(-1));
        assertThat(logic.findBy(Cell.F4), is(1));
        assertThat(logic.findBy(Cell.C5), is(-1));
        assertThat(logic.findBy(Cell.D7), is(0));
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти не по правилам своей логики.
     */
    @Test
    public void wrongMoves() {
        try {
            logic.move(Cell.C5, Cell.C3);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C5), is(0));
            assertThat(logic.findBy(Cell.C3), is(-1));
        }
        try {
            logic.move(Cell.D3, Cell.D4);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.D3), is(1));
            assertThat(logic.findBy(Cell.D4), is(-1));
        }
    }

    /**
     * "На пути фигуры находится другая фигура!" - фигура пытается пойти через клетку с другой фигурой.
     */
    @Test
    public void moveThroughAnotherFigure() {
        try {
            logic.move(Cell.C5, Cell.E6);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.C5), is(0));
            assertThat(logic.findBy(Cell.E6), is(-1));
        }
        try {
            logic.move(Cell.D3, Cell.B4);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.D3), is(1));
            assertThat(logic.findBy(Cell.B4), is(-1));
        }
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти в клетку с другой фигурой.
     */
    @Test
    public void moveToAnotherFigure() {
        try {
            logic.move(Cell.C5, Cell.D3);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C5), is(0));
            assertThat(logic.findBy(Cell.D3), is(1));
        }
        try {
            logic.move(Cell.D3, Cell.C5);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.D3), is(1));
            assertThat(logic.findBy(Cell.C5), is(0));
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
