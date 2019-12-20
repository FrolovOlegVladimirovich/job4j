package ru.job4j.tictactoegame;

/**
 * A set of actions to check the winning combinations in the game.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface CheckWin {
    /**
     * Checks column.
     * @param symbol Checked character.
     * @return true if validated.
     */
    boolean checkColumn(String symbol);

    /**
     * Checks line.
     * @param symbol Checked character.
     * @return true if validated.
     */
    boolean checkLine(String symbol);

    /**
     * Checks diagonal from left top to right bottom.
     * @param symbol Checked character.
     * @return true if validated.
     */
    boolean checkLeftDiagonal(String symbol);

    /**
     * Checks diagonal from right top to left bottom.
     * @param symbol Checked character.
     * @return true if validated.
     */
    boolean checkRightDiagonal(String symbol);
}