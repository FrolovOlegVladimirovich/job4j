package ru.job4j.multithreading.bomberman;

/**
 * The game util class.
 * Contains playing field, bomberman, monsters, blocks.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Game {
    private final Board board;
    private final Runnable bomberman;
    private final Runnable monsters;
    private final Blocks blocks;

    /**
     * @param boardSize Playing field size.
     * @param numOfMonsters Number of monsters.
     * @param percentOfBlocks Percentage of blocks from the playing field size.
     */
    public Game(int boardSize, int numOfMonsters, int percentOfBlocks) {
        board = new Board(boardSize);
        bomberman = new Bomberman(board);
        monsters = new Monsters(board, numOfMonsters);
        blocks = new Blocks(board, percentOfBlocks);
    }

    /**
     * Starts the game without bomberman moves.
     * To implement movements of the bomberman, the changePosition method in Bomberman class should be used.
     * @throws InterruptedException Exception from join method.
     */
    public void start() throws InterruptedException {
        blocks.setBlocks();
        Thread monstersTh = new Thread(monsters);
        monstersTh.setDaemon(true);
        Thread bombermanTh = new Thread(bomberman);
        monstersTh.start();
        bombermanTh.start();
        bombermanTh.join();
    }

    public static void main(String[] args) throws InterruptedException {
        new Game(6, 3, 10).start();
    }
}