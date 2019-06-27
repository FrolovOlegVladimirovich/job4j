package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.RookBlack;
import ru.job4j.chess.figures.white.RookWhite;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест фигуры "Ладья".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.07.2019
 * @version 1.0
 */
public class RookTest {
    private final Logic logic = new Logic();

    /**
     * Перед стартом тестов добавляет необходимые фигуры на игровое поле.
     */
    @Before
    public void addFigures() {
        logic.add(new RookBlack(Cell.C4));
        logic.add(new RookWhite(Cell.F6));

    }

    /**
     * Черная ладья идет:
     * 1. на одну клетку вверх,
     * 2. на четыре клетки вправо,
     * 3. на одну клетку влево,
     * 4. на одну клетку вниз,
     * 5. на пять клеток влево,
     * 6. на две клетки вниз.
     *
     * Белая ладья идет:
     * 1. на три клетки вверх,
     * 2. на четыре клетки влево,
     * 3. на две клетки вниз,
     * 4. на шесть клеток вправо.
     */
    @Test
    public void rightMoves() {
        logic.move(Cell.C4, Cell.C3);
        logic.move(Cell.C3, Cell.G3);
        logic.move(Cell.G3, Cell.F3);
        logic.move(Cell.F3, Cell.F4);
        logic.move(Cell.F4, Cell.A4);
        logic.move(Cell.A4, Cell.A6);
        logic.move(Cell.F6, Cell.F3);
        logic.move(Cell.F3, Cell.B3);
        logic.move(Cell.B3, Cell.B5);
        logic.move(Cell.B5, Cell.H5);

        assertThat(logic.findBy(Cell.C4), is(-1));
        assertThat(logic.findBy(Cell.A6), is(0));
        assertThat(logic.findBy(Cell.F6), is(-1));
        assertThat(logic.findBy(Cell.H5), is(1));
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти по диагонали.
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
            logic.move(Cell.F6, Cell.H4);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.F6), is(1));
            assertThat(logic.findBy(Cell.H4), is(-1));
        }
    }

    /**
     * "На пути фигуры находится другая фигура!" - фигура пытается пойти через клетку с другой фигурой.
     */
    @Test
    public void moveThroughAnotherFigure() {
        try {
            logic.move(Cell.C4, Cell.C6);
            logic.move(Cell.C6, Cell.H6);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.C4), is(-1));
            assertThat(logic.findBy(Cell.C6), is(0));
            assertThat(logic.findBy(Cell.H6), is(-1));
        }
        try {
            logic.move(Cell.F6, Cell.B6);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.F6), is(1));
            assertThat(logic.findBy(Cell.B6), is(-1));
        }
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти в клетку с другой фигурой.
     */
    @Test
    public void moveToAnotherFigure() {
        try {
            logic.move(Cell.C4, Cell.F4);
            logic.move(Cell.F4, Cell.F6);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C4), is(-1));
            assertThat(logic.findBy(Cell.F4), is(0));
            assertThat(logic.findBy(Cell.F6), is(1));
        }
        try {
            logic.move(Cell.F6, Cell.F4);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.F6), is(1));
            assertThat(logic.findBy(Cell.F4), is(0));
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
