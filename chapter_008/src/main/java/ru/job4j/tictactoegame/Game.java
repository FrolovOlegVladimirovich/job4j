package ru.job4j.tictactoegame;

import java.util.List;

/**
 * Tic Tac Toe console game.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Game {
    protected final Board board;
    private final Player first;
    private final Player second;
    private final List<String> possibleFigures;

    /**
     * Default constructor.
     * @param board Game board.
     * @param first Player which starts game first.
     * @param second Second player.
     */
    public Game(Board board, Player first, Player second) {
        this.board = board;
        this.first = first;
        this.second = second;
        this.possibleFigures = List.of("x", "o");
    }

    /**
     * Init game.
     */
    public void init() {
        play(preparePlayers());
    }

    /**
     * Prepare players to the game.
     * Sets figures for the players.
     * @return Ready players.
     */
    private Player[] preparePlayers() {
        String player2Figure;
        if (possibleFigures.indexOf(firstPlayerChooseFigure()) == 0) {
            player2Figure = possibleFigures.get(1);
        } else {
            player2Figure = possibleFigures.get(0);
        }
        second.setFigure(player2Figure);
        return new Player[] {first, second};
    }

    /**
     * Main game cycle.
     * @param players Players.
     * @return Winner.
     */
    protected Player play(Player[] players) {
        Player winner = null;
        int i = 0;
        while (true) {
            board.show();
            if (move(players[i])) {
                winner = players[i];
                showWinnerMessage(winner);
                break;
            }
            if (!board.containsEmptyCells()) {
                showDrawMessage();
                break;
            }
            i++;
            if (i == 2) {
                i = 0;
            }
        }
        return winner;
    }

    /**
     * Shows winner text message to console.
     * @param winner Winner.
     */
    protected void showWinnerMessage(Player winner) {
        System.out.println(String.format("The winner is %s! End of the game.", winner.getFigure()));
    }

    /**
     * Shows dead heat text message to console.
     */
    protected void showDrawMessage() {
        System.out.println("Dead heat! End of the game.");
    }

    /**
     * Checks the player’s move.
     * @param player Player.
     * @return true if the player’s move is successful.
     */
    private boolean move(Player player) {
        boolean result = false;
        boolean exit = true;
        while (exit) {
            var inputCoordinates = player.chooseCellCoordinatesToMove(board);
            if (board.addFigure(player.getFigure(), inputCoordinates[0], inputCoordinates[1])) {
                if (isWin(player.getFigure())) {
                    board.show();
                    result = true;
                }
                exit = false;
            } else {
                System.out.println("Cell not available. Repeat the move.");
            }
        }
        return result;
    }

    /**
     * First player choose figure.
     * @return Chosen figure.
     */
    private String firstPlayerChooseFigure() {
        boolean exit = true;
        String firstChoose = null;
        while (exit) {
            firstChoose = first.chooseFigure(possibleFigures);
            if (possibleFigures.contains(firstChoose)) {
                first.setFigure(firstChoose);
                exit = false;
            } else {
                System.out.println(
                        String.format(
                                "Symbol should be %s or %s",
                                possibleFigures.get(0),
                                possibleFigures.get(1)
                        )
                );
            }
        }
        return firstChoose;
    }

    /**
     * Checks the winning combination.
     * @param symbol Figure.
     * @return true if there is a winning combination on the playing board.
     */
    private boolean isWin(String symbol) {
        return     board.checkLine(symbol)
                || board.checkColumn(symbol)
                || board.checkLeftDiagonal(symbol)
                || board.checkRightDiagonal(symbol);
    }

    /**
     * Main method to start Tic Tac Toe game.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        Game game = new GameWithNumOfWins(new Board(4), new Computer(), new User(), 5);
        game.init();
    }
}