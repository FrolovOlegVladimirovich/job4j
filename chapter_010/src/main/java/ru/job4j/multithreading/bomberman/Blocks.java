package ru.job4j.multithreading.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Blocks unit.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Blocks {
    private static final Logger LOG = LogManager.getLogger(Blocks.class.getName());
    private final Board board;
    private final Cell[] positions;

    /**
     * @param board Playing field.
     * @param percentOfBlocks Percentage of blocks from the playing field size.
     */
    public Blocks(Board board, int percentOfBlocks) {
        this.board = board;
        this.positions = new Cell[((int) Math.pow(board.getBoardSize(), 2) / percentOfBlocks) + 1];
    }

    /**
     * Blocks the cells of the playing field on which the Blocks-unit stand.
     */
    public void setBlocks() {
        for (int i = 0; i < positions.length; i++) {
            do {
                positions[i] = board.getRandomPosition();
            } while (board.getLockCell(positions[i]).isLocked());
            board.lock(positions[i]);
            LOG.info(String.format("Block №%d is mounted on the coordinates x=%d y=%d",
                    i,
                    positions[i].getX(),
                    positions[i].getY())
            );
        }
    }
}