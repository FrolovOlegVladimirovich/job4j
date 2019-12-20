package ru.job4j.tictactoegame;

import java.util.Arrays;
import java.util.Objects;

/**
 * Tic Tac Toe game board.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Board implements ShowBoard, CheckWin {
    private final int size;
    private String[][] coordinates;

    /**
     * Default constructor sets size of board is 3.
     */
    public Board() {
        this.size = 3;
        this.coordinates = new String[size][size];
    }

    /**
     * Sets different size of board.
     * @param size Size of board.
     */
    public Board(int size) {
        this.size = size;
        this.coordinates = new String[size][size];
    }

    /**
     * Adds figure on the game board.
     * @param symbol Figure.
     * @param coordX X coordinate.
     * @param coordY Y coordinate.
     * @return true if the figure is successfully added to the field.
     */
    public boolean addFigure(String symbol, int coordX, int coordY) {
        boolean result = false;
        if (coordX < size && coordX >= 0 && coordY < size && coordY >= 0 && cellIsEmpty(coordX, coordY)) {
            coordinates[coordX][coordY] = symbol;
            result = true;
        }
        return result;
    }

    /**
     * Clears the playing field of all figures.
     */
    public void clearBoard() {
        coordinates = new String[size][size];
    }

    /**
     * Return true if cell is empty.
     * @param coordX X coordinate.
     * @param coordY Y coordinate.
     * @return true if cell is empty.
     */
    private boolean cellIsEmpty(int coordX, int coordY) {
        return coordinates[coordX][coordY] == null;
    }

    /**
     * Get coordinates with current location of figures on the game board.
     * @return Coordinates with figures.
     */
    public String[][] getCoordinates() {
        return coordinates;
    }

    /**
     * Checks if the playing board contains empty cells.
     * @return true if the playing board contains empty cells.
     */
    public boolean containsEmptyCells() {
        for (String[] line : coordinates) {
            for (String symbol : line) {
                if (symbol == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Shows tic tac toe game board.
     * @return String to console output show.
     */
    @Override
    public String show() {
        StringBuilder builder = new StringBuilder();
        for (String[] line: coordinates) {
            for (String symbol : line) {
                builder.append(String.format("%s ",
                        Objects.requireNonNullElse(symbol, "_"))
                );
            }
            builder.append(System.lineSeparator());
        }
        String result = builder.toString();
        System.out.println(result);
        return result;
    }

    /**
     * Checks column.
     * @param symbol Checked character.
     * @return true if validated.
     */
    @Override
    public boolean checkColumn(String symbol) {
        boolean result = false;
        int x = 0;
        int y = 0;
        while (x < coordinates.length && y < coordinates.length) {
            var cell = coordinates[x][y];
            if (symbol.equals(cell)) {
                result = true;
                x++;
            } else {
                result = false;
                x = 0;
                y++;
            }
        }
        return result;
    }

    /**
     * Checks line.
     * @param symbol Checked character.
     * @return true if validated.
     */
    @Override
    public boolean checkLine(String symbol) {
        boolean result = false;
        for (String[] line : coordinates) {
            for (String figure : line) {
                if (symbol.equals(figure)) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Checks diagonal from left top to right bottom.
     * @param symbol Checked character.
     * @return true if validated.
     */
    @Override
    public boolean checkLeftDiagonal(String symbol) {
        boolean result = true;
        int size = coordinates.length;
        int x = 0;
        int y = 0;
        while (x < size && y < size) {
            if (symbol.equals(coordinates[x][y])) {
                x++;
                y++;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Checks diagonal from right top to left bottom.
     * @param symbol Checked character.
     * @return true if validated.
     */
    @Override
    public boolean checkRightDiagonal(String symbol) {
        boolean result = true;
        int size = coordinates.length;
        int x = 0;
        int y = size - 1;
        while (x < size && y >= 0) {
            if (symbol.equals(coordinates[x][y])) {
                x++;
                y--;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board = (Board) o;
        return size == board.size
                && Arrays.equals(coordinates, board.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }
}