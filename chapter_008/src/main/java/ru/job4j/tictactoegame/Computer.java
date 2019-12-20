package ru.job4j.tictactoegame;

import java.util.ArrayList;
import java.util.List;

/**
 * Computer behavior in a tic-tac-toe game.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Computer implements Player {
    private String figure;
    private int countWins;

    public Computer() {
    }

    /**
     * Gets number of player wins.
     * @return Number of wins.
     */
    @Override
    public int getCountWins() {
        return countWins;
    }

    /**
     * Sets number of player wins.
     * @param countWins Number of wins.
     */
    @Override
    public void setCountWins(int countWins) {
        this.countWins = countWins;
    }

    /**
     * Sets a figure to be played.
     * @param figure Figure.
     */
    @Override
    public void setFigure(String figure) {
        this.figure = figure;
    }

    /**
     * Gets the figure which plays.
     * @return Figure.
     */
    @Override
    public String getFigure() {
        return figure;
    }

    /**
     * Selects the cell coordinates for the move.
     * @param board Game board.
     * @return Coordinates.
     */
    @Override
    public int[] chooseCellCoordinatesToMove(Board board) {
        List<int[]> freeCoordinates = new ArrayList<>();
        String[][] coords = board.getCoordinates();
        for (int x = 0; x < coords.length; x++) {
            for (int y = 0; y < coords.length; y++) {
                if (coords[x][y] == null) {
                    int[] coordinates = new int[] {x, y};
                    freeCoordinates.add(coordinates);
                }
            }
        }
        return freeCoordinates.get(getRandomNum(freeCoordinates.size()));
    }

    /**
     * Selects figure to play.
     * @param possibleFigures List of possible figures.
     * @return the selected figure.
     */
    @Override
    public String chooseFigure(List<String> possibleFigures) {
        return possibleFigures.get(getRandomNum(2));
    }

    /**
     * Gets a random number in a given range from zero to limit parameter.
     * @param limit Maximum random number.
     * @return Random number.
     */
    private int getRandomNum(int limit) {
        return (int) (Math.random() * limit);
    }
}