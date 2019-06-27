package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.*;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.PawnBlack;
import ru.job4j.chess.figures.white.PawnWhite;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест фигуры "Пешка".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.07.2019
 * @version 1.0
 */
public class PawnTest {
    private final Logic logic = new Logic();

    /**
     * Перед стартом тестов добавляет необходимые фигуры на игровое поле.
     */
    @Before
    public void addFigures() {
        logic.add(new PawnBlack(Cell.A7));
        logic.add(new PawnBlack(Cell.B7));
        logic.add(new PawnBlack(Cell.B6));
        logic.add(new PawnBlack(Cell.C5));

        logic.add(new PawnWhite(Cell.H2));
        logic.add(new PawnWhite(Cell.G2));
        logic.add(new PawnWhite(Cell.G3));
        logic.add(new PawnWhite(Cell.F4));
    }

    /**
     * Идет на одну клетку вперед.
     */
    @Test
    public void moveOneStepForward() {
        logic.move(Cell.A7, Cell.A6);
        logic.move(Cell.H2, Cell.H3);
        assertThat(logic.findBy(Cell.A7), is(-1));
        assertThat(logic.findBy(Cell.A6), is(0));
        assertThat(logic.findBy(Cell.H2), is(-1));
        assertThat(logic.findBy(Cell.H3), is(4));
    }


    /**
     * Идет на две клетки вперед с изначальной позиции.
     */
    @Test
    public void startMoveTwoStepsForward() {
        logic.move(Cell.A7, Cell.A5);
        logic.move(Cell.H2, Cell.H4);
        assertThat(logic.findBy(Cell.A7), is(-1));
        assertThat(logic.findBy(Cell.A5), is(0));
        assertThat(logic.findBy(Cell.H2), is(-1));
        assertThat(logic.findBy(Cell.H4), is(4));
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти в клетку с другой фигурой.
     */
    @Test
    public void moveToAnotherFigure() {
        try {
            logic.move(Cell.B7, Cell.B6);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.B7), is(1));
            assertThat(logic.findBy(Cell.B6), is(2));
        }
        try {
            logic.move(Cell.G2, Cell.G3);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.G2), is(5));
            assertThat(logic.findBy(Cell.G3), is(6));
        }
    }

    /**
     * "На пути фигуры находится другая фигура!" - фигура пытается пойти через клетку с другой фигурой.
     */
    @Test
    public void moveThroughAnotherFigure() {
        try {
            logic.move(Cell.B7, Cell.B5);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.B7), is(1));
            assertThat(logic.findBy(Cell.B5), is(-1));
        }
        try {
            logic.move(Cell.G2, Cell.G4);
        } catch (OccupiedWayException owe) {
            assertThat(logic.findBy(Cell.G2), is(5));
            assertThat(logic.findBy(Cell.G4), is(-1));
        }
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти на две клетки вперед не с изначальной позиции.
     */
    @Test
    public void moveTwoStepsForwardFromNoStartPos() {
        try {
            logic.move(Cell.C5, Cell.C3);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C5), is(3));
            assertThat(logic.findBy(Cell.C3), is(-1));
        }
        try {
            logic.move(Cell.F4, Cell.F6);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.F4), is(7));
            assertThat(logic.findBy(Cell.F6), is(-1));
        }
    }

    /**
     * "Фигура не найдена!" - фигура отсутствует.
     */
    @Test
    public void figureNotFound() {
        try {
            logic.move(Cell.D7, Cell.D6);
        } catch (FigureNotFoundException fnfe) {
            assertThat(logic.findBy(Cell.D7), is(-1));
            assertThat(logic.findBy(Cell.D6), is(-1));
        }
        try {
            logic.move(Cell.E2, Cell.E3);
        } catch (FigureNotFoundException fnfe) {
            assertThat(logic.findBy(Cell.E2), is(-1));
            assertThat(logic.findBy(Cell.E3), is(-1));
        }
    }

    /**
     * "Данный ход невозможен!" - фигура пытается пойти на одну клетку назад.
     */
    @Test
    public void wrongMoves() {
        try {
            logic.move(Cell.C5, Cell.C6);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.C5), is(3));
            assertThat(logic.findBy(Cell.C6), is(-1));
        }
        try {
            logic.move(Cell.F4, Cell.F3);
        } catch (ImpossibleMoveException ime) {
            assertThat(logic.findBy(Cell.F4), is(7));
            assertThat(logic.findBy(Cell.F3), is(-1));
        }
    }
}