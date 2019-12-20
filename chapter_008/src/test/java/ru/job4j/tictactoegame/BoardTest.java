package ru.job4j.tictactoegame;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void whenAddFigureOnEmptyCellResultIsTrue() {
        String x = "X";
        Board board = new Board();

        boolean result = board.addFigure(x, 1, 1);

        assertTrue(result);
    }

    @Test
    public void whenAddFigureOnBusyCellResultIsFalse() {
        String x = "X";
        String o = "O";
        Board board = new Board();

        board.addFigure(o, 1, 1);
        boolean result = board.addFigure(x, 1, 1);

        assertFalse(result);
    }

    @Test
    public void whenAddFigureOnNonexistentCoordinatesResultIsFalse() {
        String x = "X";
        Board board = new Board();

        boolean result = board.addFigure(x, 10, 10);

        assertFalse(result);
    }

    @Test
    public void whenAddFigureXAndClearBoardThanBoardContainsEmptyFigures() {
        String x = "X";
        Board board = new Board();

        board.addFigure(x, 1, 1);
        board.clearBoard();
        String result = board.show();
        String expect = String.format("%s%n%<s%n%<s%n", "_ _ _ ");

        assertThat(result, is(expect));
    }

    @Test
    public void whenBoardSizeIs5ThanBoardContains5EmptyFigures() {
        Board board = new Board(5);

        String result = board.show();
        String expect = String.format("%s%n%<s%n%<s%n%<s%n%<s%n", "_ _ _ _ _ ");

        assertThat(result, is(expect));
    }

    @Test
    public void whenClearBoardThanBoardContainsNull() {
        Board board = new Board();
        board.addFigure("x", 0, 0);

        board.clearBoard();

        for (String[] line: board.getCoordinates()) {
            for (String cell : line) {
                assertNull(cell);
            }
        }
    }

    @Test
    public void whenCellsIsEmptyResultIsTrue() {
        Board board = new Board();

        assertTrue(board.containsEmptyCells());
    }

    @Test
    public void whenCellsIsFullResultIsFalse() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("x", 0, 1);
        board.addFigure("x", 0, 2);
        board.addFigure("x", 1, 0);
        board.addFigure("x", 1, 1);
        board.addFigure("x", 1, 2);
        board.addFigure("x", 2, 0);
        board.addFigure("x", 2, 1);
        board.addFigure("x", 2, 2);

        assertFalse(board.containsEmptyCells());
    }

    @Test
    public void whenOneCellIsEmptyResultIsTrue() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("x", 0, 1);
        board.addFigure("x", 0, 2);
        board.addFigure("x", 1, 0);
        board.addFigure("x", 1, 1);
        board.addFigure("x", 1, 2);
        board.addFigure("x", 2, 0);
        board.addFigure("x", 2, 1);

        assertTrue(board.containsEmptyCells());
    }

    @Test
    public void whenSomeCellIsEmptyResultIsTrue() {
        Board board = new Board();
        board.addFigure("x", 0, 1);
        board.addFigure("x", 1, 0);
        board.addFigure("x", 1, 2);
        board.addFigure("x", 2, 1);

        assertTrue(board.containsEmptyCells());
    }
}