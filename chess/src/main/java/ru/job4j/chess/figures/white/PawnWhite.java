package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.Pawn;

/**
 * Фигура "Белая пешка".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 27.06.2019
 */
public class PawnWhite extends Pawn implements Figure {
    private final Cell position;

    public PawnWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public String toString() {
        return "PawnWhite";
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
