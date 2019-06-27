package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.Knight;

/**
 * Фигура "Белый конь".
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version $Id$
 * @since 05.07.2019
 */
public class KnightWhite extends Knight implements Figure {
    private final Cell position;

    public KnightWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
