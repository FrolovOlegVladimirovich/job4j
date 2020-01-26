package ru.job4j.multithreading.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Monsters thread.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Monsters implements Runnable {
    private static final Logger LOG = LogManager.getLogger(Monsters.class.getName());
    private final Board board;
    private final Cell[] positions;

    public Monsters(Board board, int numOfMonsters) {
        this.board = board;
        this.positions = new Cell[numOfMonsters];
    }

    /**
     * Sets monsters start positions.
     */
    private void setPositions() {
        Cell rand;
        for (int i = 0; i < positions.length; i++) {
            do {
                rand = board.getRandomPosition();
            } while (!board.getLockCell(rand).tryLock());
            positions[i] = rand;
            LOG.info(String.format("Monster №%d is mounted on the coordinate x=%d y=%d",
                    i,
                    positions[i].getX(),
                    positions[i].getY())
            );
        }
    }

    /**
     * Validates random move.
     * Unit can only go one cell to the right, left, up or down & and shouldn't go beyond the playing field.
     * @param currentPos Current monster position.
     * @return cell the monster will go to.
     */
    private Cell randomMove(Cell currentPos) {
        Cell result;
        int x;
        int y;
        do {
            if ("x".equals(new Random().nextBoolean() ? "y" : "x")) {
                x = new Random().nextBoolean() ? 1 : -1;
                result = new Cell(currentPos.getX() + x, currentPos.getY());
            } else {
                y = new Random().nextBoolean() ? 1 : -1;
                result = new Cell(currentPos.getX(), currentPos.getY() + y);
            }
        } while (!((result.getX() > 0 && result.getX() < board.getBoardSize())
                && (result.getY() > 0 && result.getY() < board.getBoardSize())));
        return result;
    }

    /**
     * The monsters lifecycle thread.
     *
     * The monsters thread should mark as daemon.
     * If monster goes to a blocked cell occupied by another thread,
     * it tries to block the cell for 0.5 seconds.
     * If the bomberman is in the locked cell, then the game is over.
     * If cell is blocked by a Block-unit or a monsters thread,
     * the monster waits 0.5 seconds and goes to another cell.
     * If cell is not locked, the monster moves and waits 1 second.
     *
     */
    @Override
    public void run() {
        LOG.info("Monsters thread has been started.");
        setPositions();
        do {
            try {
                Cell move;
                for (int i = 0; i < positions.length; i++) {
                    do {
                        move = randomMove(positions[i]);
                        LOG.info(String.format("Monster №%d is going on coordinate x=%d y=%d",
                                i,
                                move.getX(),
                                move.getY())
                        );
                        if (board.getLockCell(move).isHeldByCurrentThread()) {
                            LOG.info(String.format("Monster №%d is going on coordinate in which another monster stands. Waiting for 0,5 second",
                                    i)
                            );
                            Thread.sleep(500);
                        } else if (board.getLockCell(move).isLocked()) {
                            LOG.info(String.format("Monster №%d is going to coordinate where possible should Bomberman.",
                                    i)
                            );
                            board.getLockCell(move).tryLock(500, TimeUnit.MILLISECONDS);
                        }
                    } while (!board.move(positions[i], move));
                    positions[i].setX(move.getX());
                    positions[i].setY(move.getY());
                    LOG.info(String.format("Monster №%d successfully entered the coordinate x=%d y=%d",
                            i,
                            move.getX(),
                            move.getY())
                    );
                }
                LOG.info("Monsters wait 1 second until next turn.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } while (true);
    }
}