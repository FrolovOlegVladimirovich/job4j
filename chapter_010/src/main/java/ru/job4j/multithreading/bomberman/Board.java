package ru.job4j.multithreading.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Bomberman playing field.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Board {
    private final ReentrantLock[][] board;

    public Board(int boardSize) {
        this.board = new ReentrantLock[boardSize][boardSize];
        fillBoard();
    }

    public int getBoardSize() {
        return board.length;
    }

    Cell getRandomPosition() {
        int boardSize = board.length;
        int x = (int) (Math.random() * boardSize);
        int y = (int) (Math.random() * boardSize);
        return new Cell(x, y);
    }

    public ReentrantLock getLockCell(Cell cell) {
        return board[cell.getX()][cell.getY()];
    }

    public boolean lock(Cell cell) {
        return board[cell.getX()][cell.getY()].tryLock();
    }

    private void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Moves unit from source to dest cell.
     * @param source Source cell.
     * @param dest Destination cell.
     * @return True if dest cell was blocked & source cell was unlocked.
     * @throws InterruptedException Exception from tryLock.
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        if (!((dest.getX() > 0 && dest.getX() < board.length) && (dest.getY() > 0 && dest.getY() < board.length))) {
            return false;
        }
        var startPos = board[source.getX()][source.getY()];
        var destPos = board[dest.getX()][dest.getY()];
        if (!destPos.isHeldByCurrentThread() && destPos.tryLock(500, TimeUnit.MILLISECONDS)) {
            startPos.unlock();
            return true;
        }
        return false;
    }
}