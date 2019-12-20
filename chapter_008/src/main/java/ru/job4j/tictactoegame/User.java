package ru.job4j.tictactoegame;

import java.util.*;

/**
 * User behavior in a tic-tac-toe game.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class User implements Input, Player {
    private String figure;
    private int countWins;

    public User() {
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
        System.out.println(String.format("You play %s", figure));
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
        var x = getUserInputCoordinate("Enter the coordinate of the vertical cell : ");
        var y = getUserInputCoordinate("Enter the coordinate of the horizontal cell : ");
        return new int[]{x, y};
    }

    /**
     * Selects figure to play.
     * @param possibleFigures List of possible figures.
     * @return the selected figure.
     */
    @Override
    public String chooseFigure(List<String> possibleFigures) {
        return ask(
                String.format("What figure will you play? Enter %s or %s",
                        possibleFigures.get(0),
                        possibleFigures.get(1)
                )
        ).trim();
    }

    /**
     * Gets user input coordinate to move.
     * @param request Request.
     * @return Coordinate.
     */
    private int getUserInputCoordinate(String request) {
        int result = -1;
        while (result == -1) {
            try {
                result = Integer.valueOf(ask(request));
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException. Input correct number.");
            }
        }
        return result;
    }

    /**
     * Passes the request/question to the user and receives a replies.
     * @param request to the user.
     * @return User response.
     */
    @Override
    public String ask(String request) {
        System.out.println(request);
        return new Scanner(System.in).nextLine();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(figure, user.figure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(figure);
    }
}