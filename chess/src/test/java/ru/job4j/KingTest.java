package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.KingBlack;
import ru.job4j.chess.figures.white.KingWhite;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест фигуры "Король".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.07.2019
 * @version 1.0
 */
public class KingTest {
    private final Logic logic = new Logic();

    /**
     * Перед стартом тестов добавляет необходимые фигуры на игровое поле.
     */
    @Before
    public void addFigures() {
        logic.add(new KingBlack(Cell.C4));
        logic.add(new KingWhite(Cell.D3));
    }

    /**
     * Проверяет ход на одну клетку каждой фигуры по диагонали и вертикали.
     */
    @Test
    public void rightMoves() {
        logic.move(Cell.C4, Cell.C3);
        logic.move(Cell.C3, Cell.B2);
        logic.move(Cell.B2, Cell.A3);

        logic.move(Cell.D3, Cell.D4);
        logic.move(Cell.D4, Cell.C3);
        logic.move(Cell.C3, Cell.C2);

        assertThat(logic.findBy(Cell.C4), is(-1));
        assertThat(logic.findBy(Cell.A3), is(0));
        assertThat(logic.findBy(Cell.D3), is(-1));
        assertThat(logic.findBy(Cell.C2), is(1));
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти на несколько клеток за один ход.
     */
    @Test
    public void wrongMoves() {
        try {
            logic.move(Cell.C4, Cell.A6);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C4), is(0));
            assertThat(logic.findBy(Cell.A6), is(-1));
        }
        try {
            logic.move(Cell.D3, Cell.D1);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.D3), is(1));
            assertThat(logic.findBy(Cell.D1), is(-1));
        }
    }

    /**
     * "На пути фигуры находится другая фигура!" - фигура пытается пойти через клетку с другой фигурой.
     */
    @Test
    public void moveThroughAnotherFigure() {
        try {
            logic.move(Cell.D3, Cell.B5);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.D3), is(1));
            assertThat(logic.findBy(Cell.B5), is(-1));
        }
        try {
            logic.move(Cell.C4, Cell.E2);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C4), is(0));
            assertThat(logic.findBy(Cell.E2), is(-1));
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
            logic.move(Cell.A1, Cell.A2);
        } catch (FigureNotFoundException fnfe) {
            assertThat(logic.findBy(Cell.A1), is(-1));
            assertThat(logic.findBy(Cell.A2), is(-1));
        }
    }
}
