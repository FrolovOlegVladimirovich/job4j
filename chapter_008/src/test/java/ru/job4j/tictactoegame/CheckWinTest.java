package ru.job4j.tictactoegame;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckWinTest {

    /**
     * X O X
     * _ _ _
     * _ _ _
     */
    @Test
    public void checkLineTestWhenAnotherSymbol() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("o", 0, 1);
        board.addFigure("x", 0, 2);

        boolean result = board.checkLine("x");

        assertFalse(result);
    }

    /**
     * X _ X
     * _ _ _
     * _ _ _
     */
    @Test
    public void checkLineTestWhenNoSymbol() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("x", 0, 2);

        boolean result = board.checkLine("x");

        assertFalse(result);
    }

    /**
     * O O O
     * _ _ _
     * _ _ _
     */
    @Test
    public void checkLineTestWhenOK1() {
        Board board = new Board();
        board.addFigure("o", 0, 0);
        board.addFigure("o", 0, 1);
        board.addFigure("o", 0, 2);

        boolean result = board.checkLine("o");

        assertTrue(result);
    }

    /**
     * _ _ _
     * O O O
     * _ _ _
     */
    @Test
    public void checkLineTestWhenOK2() {
        Board board = new Board();
        board.addFigure("o", 1, 0);
        board.addFigure("o", 1, 1);
        board.addFigure("o", 1, 2);

        boolean result = board.checkLine("o");

        assertTrue(result);
    }

    /**
     * _ _ _
     * _ _ _
     * O O O
     */
    @Test
    public void checkLineTestWhenOK3() {
        Board board = new Board();
        board.addFigure("o", 2, 0);
        board.addFigure("o", 2, 1);
        board.addFigure("o", 2, 2);

        boolean result = board.checkLine("o");

        assertTrue(result);
    }

    /**
     * _ _ _
     * _ _ _
     * _ _ _
     */
    @Test
    public void checkLineWhenNothing() {
        Board board = new Board();

        boolean result = board.checkLine("o");

        assertFalse(result);
    }

    /**
     * X _ _
     * O _ _
     * X _ _
     */
    @Test
    public void checkColumnTestWhenAnotherSymbol() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("o", 1, 0);
        board.addFigure("x", 2, 0);

        boolean result = board.checkColumn("x");

        assertFalse(result);
    }

    /**
     * X _ _
     * _ _ _
     * X _ _
     */
    @Test
    public void checkColumnTestWhenNoSymbol() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("x", 2, 0);

        boolean result = board.checkColumn("x");

        assertFalse(result);
    }

    /**
     * O _ _
     * O _ _
     * O _ _
     */
    @Test
    public void checkColumnTestWhenOK1() {
        Board board = new Board();
        board.addFigure("o", 0, 0);
        board.addFigure("o", 1, 0);
        board.addFigure("o", 2, 0);

        boolean result = board.checkColumn("o");

        assertTrue(result);
    }

    /**
     * _ O _
     * _ O _
     * _ O _
     */
    @Test
    public void checkColumnTestWhenOK2() {
        Board board = new Board();
        board.addFigure("o", 0, 1);
        board.addFigure("o", 1, 1);
        board.addFigure("o", 2, 1);

        boolean result = board.checkColumn("o");

        assertTrue(result);
    }

    /**
     * _ _ O
     * _ _ O
     * _ _ O
     */
    @Test
    public void checkColumnTestWhenOK3() {
        Board board = new Board();
        board.addFigure("o", 0, 2);
        board.addFigure("o", 1, 2);
        board.addFigure("o", 2, 2);

        boolean result = board.checkColumn("o");

        assertTrue(result);
    }

    /**
     * _ _ _
     * _ _ _
     * _ _ _
     */
    @Test
    public void checkColumnWhenNothing() {
        Board board = new Board();

        boolean result = board.checkColumn("o");

        assertFalse(result);
    }

    /**
     * X _ _
     * _ O _
     * _ _ X
     */
    @Test
    public void checkLeftDiagonalTestWhenAnotherSymbol() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("o", 1, 1);
        board.addFigure("x", 2, 2);

        boolean result = board.checkLeftDiagonal("x");

        assertFalse(result);
    }

    /**
     * _ _ X
     * _ O _
     * X _ _
     */
    @Test
    public void checkRightDiagonalTestWhenAnotherSymbol() {
        Board board = new Board();
        board.addFigure("x", 0, 2);
        board.addFigure("o", 1, 1);
        board.addFigure("x", 2, 0);

        boolean result = board.checkRightDiagonal("x");

        assertFalse(result);
    }

    /**
     * X _ _
     * _ _ _
     * _ _ X
     */
    @Test
    public void checkLeftDiagonalTestWhenNoSymbol() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("x", 2, 2);

        boolean result = board.checkLeftDiagonal("x");

        assertFalse(result);
    }

    /**
     * _ _ X
     * _ _ _
     * X _ _
     */
    @Test
    public void checkRightDiagonalTestWhenNoSymbol() {
        Board board = new Board();
        board.addFigure("x", 0, 2);
        board.addFigure("x", 2, 0);

        boolean result = board.checkRightDiagonal("x");

        assertFalse(result);
    }

    /**
     * X _ _
     * _ X _
     * _ _ X
     */
    @Test
    public void checkLeftDiagonalTestWhenOK() {
        Board board = new Board();
        board.addFigure("x", 0, 0);
        board.addFigure("x", 1, 1);
        board.addFigure("x", 2, 2);

        boolean result = board.checkLeftDiagonal("x");

        assertTrue(result);
    }

    /**
     * _ _ X
     * _ X _
     * X _ _
     */
    @Test
    public void checkRightDiagonalTestWhenOK() {
        Board board = new Board();
        board.addFigure("x", 0, 2);
        board.addFigure("x", 1, 1);
        board.addFigure("x", 2, 0);

        boolean result = board.checkRightDiagonal("x");

        assertTrue(result);
    }

    /**
     * _ _ _
     * _ _ _
     * _ _ _
     */
    @Test
    public void checkLeftDiagonalWhenNothing() {
        Board board = new Board();

        boolean result = board.checkLeftDiagonal("o");

        assertFalse(result);
    }

    /**
     * _ _ _
     * _ _ _
     * _ _ _
     */
    @Test
    public void checkRightDiagonalWhenNothing() {
        Board board = new Board();

        boolean result = board.checkRightDiagonal("o");

        assertFalse(result);
    }
}