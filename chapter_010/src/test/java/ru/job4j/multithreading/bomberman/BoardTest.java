package ru.job4j.multithreading.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void whenDestXCoordIsMinusOutOfBoundsBoardResultIsFalse() throws InterruptedException {
    Board board = new Board(4);
    Cell source = new Cell(0, 0);
    Cell dest = new Cell(-1, 0);

    boolean result = board.move(source, dest);

    assertFalse(result);
    }

    @Test
    public void whenDestYCoordIsMinusOutOfBoundsBoardResultIsFalse() throws InterruptedException {
        Board board = new Board(4);
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(0, -1);

        boolean result = board.move(source, dest);

        assertFalse(result);
    }

    @Test
    public void whenDestXAndYCoordIsMinusOutOfBoundsBoardResultIsFalse() throws InterruptedException {
        Board board = new Board(4);
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(-1, -1);

        boolean result = board.move(source, dest);

        assertFalse(result);
    }

    @Test
    public void whenDestXCoordIsPlusOutOfBoundsBoardResultIsFalse() throws InterruptedException {
        Board board = new Board(4);
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(4, 0);

        boolean result = board.move(source, dest);

        assertFalse(result);
    }

    @Test
    public void whenDestYCoordIsPlusOutOfBoundsBoardResultIsFalse() throws InterruptedException {
        Board board = new Board(4);
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(0, 4);

        boolean result = board.move(source, dest);

        assertFalse(result);
    }

    @Test
    public void whenDestXAndYCoordIsPlusOutOfBoundsBoardResultIsFalse() throws InterruptedException {
        Board board = new Board(4);
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(4, 4);

        boolean result = board.move(source, dest);

        assertFalse(result);
    }

    @Test
    public void whenDestCoordIsNotOutOfBoundsBoardResultIsTrue() throws InterruptedException {
        Board board = new Board(4);
        Cell source = new Cell(0, 0);
        board.lock(source);
        Cell dest = new Cell(3, 3);

        boolean result = board.move(source, dest);

        assertTrue(result);
    }
}