package ru.job4j.multithreading.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Bomberman player.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Bomberman implements Runnable {
    private static final Logger LOG = LogManager.getLogger(Bomberman.class.getName());
    private final Cell currentPos;
    private final Board board;
    private final Cell moveTo;

    public Bomberman(Board board) {
        this.board = board;
        currentPos = setPosition();
        moveTo = new Cell(currentPos.getX(), currentPos.getY());
    }

    /**
     * Sets start random position.
     * @return Cell coordinates of start position.
     */
    private Cell setPosition() {
        Cell rand;
        do {
            rand = board.getRandomPosition();
        } while (board.getLockCell(rand).isLocked());
        return rand;
    }

    /**
     * Move API.
     * @param moveTo Cell the unit will go to.
     * @return True if the unit was successfully moved to the moveTo cell.
     */
    public boolean changePosition(Cell moveTo) {
        boolean result = checkMove(moveTo);
        if (result) {
            this.moveTo.setX(moveTo.getX());
            this.moveTo.setY(moveTo.getY());
        }
        return result;
    }

    /**
     * Validates unit move.
     * Unit can only go one cell to the right, left, up or down & and shouldn't go beyond the playing field.
     * @param moveTo Cell the unit will go to.
     * @return True if the unit can go to the moveTo cell.
     */
    private boolean checkMove(Cell moveTo) {
        int xSource = currentPos.getX();
        int ySource = currentPos.getY();
        int xDest = moveTo.getX();
        int yDest = moveTo.getY();
        return ((xSource == xDest && (ySource + 1 == yDest || ySource - 1 == yDest))
                || (ySource == yDest && (xSource + 1 == xDest || xSource - 1 == xDest)))
                && (!((xDest > 0 && xDest < board.getBoardSize())
                && (yDest > 0 && yDest < board.getBoardSize())));
    }

    /**
     * The bomberman lifecycle thread.
     *
     * Bomberman expects every 1 second to change coordinates for movement.
     * If the coordinates for the movement were successfully obtained,
     * the bomberman moves to the moveTo cell.
     * After his move, the bomberman waits 1 second.
     * If another thread (monsters) blocks the cell in which the bomberman stands -
     * the bomberman dies (his thread stops working), the game is over.
     */
    @Override
    public void run() {
        LOG.info("Bomberman thread has been started.");
        board.lock(currentPos);
        LOG.info(String.format("Bomberman took the coordinate x=%d y=%d",
                currentPos.getX(),
                currentPos.getY())
        );
        do {
            try {
                while ((currentPos.equals(moveTo) || !board.move(currentPos, moveTo))) {
                    Thread.sleep(1000);
                    LOG.info("Bomberman didn't make a move. Waiting for 1 sec.");
                    LOG.info(String.format("The number of threads blocking the bomberman coordinate: %d",
                            board.getLockCell(currentPos).getQueueLength())
                    );
                    if (board.getLockCell(currentPos).getQueueLength() > 0) {
                        LOG.info("Bomberman caught by a monster!");
                        Thread.currentThread().interrupt();
                    }
                }
                currentPos.setX(moveTo.getX());
                currentPos.setY(moveTo.getY());
                LOG.info(String.format("Bomberman is going on coordinate x=%d y=%d",
                        moveTo.getX(),
                        moveTo.getY())
                );
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.info("Bomberman is dead!");
                Thread.currentThread().interrupt();
            }
        } while (board.getLockCell(currentPos).getQueueLength() == 0);
    }
}