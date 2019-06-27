package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.Pawn;

/**
 * Фигура "Черная пешка".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 27.06.2019
 */
public class PawnBlack extends Pawn implements Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public String toString() {
        return "PawnBlack";
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
