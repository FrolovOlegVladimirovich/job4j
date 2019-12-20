package ru.job4j.tictactoegame;

import java.util.List;

/**
 * Tic Tac Toe player.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface Player {
    /**
     * Sets a figure to be played.
     * @param figure Figure.
     */
    void setFigure(String figure);

    /**
     * Gets the figure which plays.
     * @return Figure.
     */
    String getFigure();

    /**
     * Gets number of player wins.
     * @return Number of wins.
     */
    int getCountWins();

    /**
     * Sets number of player wins.
     * @param countWins Number of wins.
     */
    void setCountWins(int countWins);

    /**
     * Selects the cell coordinates for the move.
     * @param board Game board.
     * @return Coordinates.
     */
    int[] chooseCellCoordinatesToMove(Board board);

    /**
     * Selects figure to play.
     * @param possibleFigures List of possible figures.
     * @return the selected figure.
     */
    String chooseFigure(List<String> possibleFigures);
}