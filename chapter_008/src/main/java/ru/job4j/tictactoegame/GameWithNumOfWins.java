package ru.job4j.tictactoegame;

/**
 * Tic Tac Toe console game up to a specified number of wins.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class GameWithNumOfWins extends Game {
    final int countOfWins;

    /**
     * Default constructor.
     * @param board Game board.
     * @param first Player which starts game first.
     * @param second Second player.
     * @param countOfWins Number of wins to win the game.
     */
    public GameWithNumOfWins(Board board, Player first, Player second, int countOfWins) {
        super(board, first, second);
        this.countOfWins = countOfWins;
    }

    /**
     * Init game.
     */
    @Override
    public void init() {
        System.out.println(String.format("The game up to %s wins.", countOfWins));
        super.init();
    }

    /**
     * Main game cycle.
     * @param players Players.
     * @return Winner.
     */
    @Override
    protected Player play(Player[] players) {
        Player winner;
        while (true) {
            board.clearBoard();
            var roundWinner = super.play(players);
            if (roundWinner != null) {
                roundWinner.setCountWins(roundWinner.getCountWins() + 1);
               var maybeWin = countNumOfWins(players);
                if (maybeWin != null) {
                    winner = maybeWin;
                    System.out.println(
                            String.format("The winner is %s. Congratulations!%nEnd of the game.",
                                    winner.getFigure()
                            )
                    );
                    break;
                }
            }
        }
        return winner;
    }

    /**
     * Count number of wins.
     * @param players Players.
     * @return Winner.
     */
    private Player countNumOfWins(Player[] players) {
        Player result = null;
        System.out.print("Score: ");
        for (Player player : players) {
            showCount(player);
            if (player.getCountWins() == countOfWins) {
                result = player;
            }
        }
        System.out.println();
        return result;
    }

    /**
     * Shows count of wins.
     * @param player Players.
     */
    private void showCount(Player player) {
        var figure = player.getFigure();
        var wins = player.getCountWins();
        System.out.print(figure + "-" + wins + " ");
    }

    /**
     * Shows winner text message to console.
     * @param winner Winner.
     */
    @Override
    protected void showWinnerMessage(Player winner) {
        System.out.println(String.format("The round winner is %s!", winner.getFigure()));
    }

    /**
     * Shows dead heat text message to console.
     */
    @Override
    protected void showDrawMessage() {
        System.out.println("Dead heat!");
    }
}