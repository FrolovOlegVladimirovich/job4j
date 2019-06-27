package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.BishopBlack;
import ru.job4j.chess.figures.black.PawnBlack;
import ru.job4j.chess.figures.white.BishopWhite;
import ru.job4j.chess.figures.white.PawnWhite;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест фигуры "Слон".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.07.2019
 * @version 1.0
 */
public class BishopTest {
    private final Logic logic = new Logic();

    /**
     * Перед стартом тестов добавляет необходимые фигуры на игровое поле.
     */
    @Before
    public void addFigures() {
        logic.add(new BishopBlack(Cell.C4));
        logic.add(new BishopWhite(Cell.F6));
        logic.add(new PawnWhite(Cell.E2));
        logic.add(new PawnBlack(Cell.G7));
    }

    /**
     * Проверяет ход белой и черной фигуры слона в разные стороны по диагонали.
     */
    @Test
    public void rightMoves() {
        logic.move(Cell.C4, Cell.E6);
        logic.move(Cell.E6, Cell.F5);
        logic.move(Cell.F5, Cell.D3);
        logic.move(Cell.D3, Cell.A6);

        logic.move(Cell.F6, Cell.D4);
        logic.move(Cell.D4, Cell.E3);
        logic.move(Cell.E3, Cell.G5);
        logic.move(Cell.G5, Cell.F6);

        assertThat(logic.findBy(Cell.C4), is(-1));
        assertThat(logic.findBy(Cell.A6), is(0));
        assertThat(logic.findBy(Cell.F6), is(1));
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти не по диагонали.
     */
    @Test
    public void wrongMoves() {
        try {
            logic.move(Cell.C4, Cell.C3);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C4), is(0));
            assertThat(logic.findBy(Cell.C3), is(-1));
        }
        try {
            logic.move(Cell.F6, Cell.E6);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.F6), is(1));
            assertThat(logic.findBy(Cell.E6), is(-1));
        }
    }

    /**
     * "На пути фигуры находится другая фигура!" - фигура пытается пойти через клетку с другой фигурой.
     */
    @Test
    public void moveThroughAnotherFigure() {
        try {
            logic.move(Cell.C4, Cell.F1);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.C4), is(0));
            assertThat(logic.findBy(Cell.F1), is(-1));
        }
        try {
            logic.move(Cell.F6, Cell.H8);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.F6), is(1));
            assertThat(logic.findBy(Cell.H8), is(-1));
        }
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти в клетку с другой фигурой.
     */
    @Test
    public void moveToAnotherFigure() {
        try {
            logic.move(Cell.C4, Cell.E2);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C4), is(0));
            assertThat(logic.findBy(Cell.E2), is(2));
        }
        try {
            logic.move(Cell.F6, Cell.G7);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.F6), is(1));
            assertThat(logic.findBy(Cell.G7), is(3));
        }
    }

    /**
     * "Фигура не найдена!" - фигура отсутствует.
     */
    @Test
    public void figureNotFound() {
        try {
            logic.move(Cell.D3, Cell.E3);
        } catch (FigureNotFoundException fnfe) {
            assertThat(logic.findBy(Cell.D3), is(-1));
            assertThat(logic.findBy(Cell.E3), is(-1));
        }
    }
}
