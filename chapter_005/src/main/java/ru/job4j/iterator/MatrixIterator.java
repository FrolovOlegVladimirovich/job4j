package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двухмерного массива int[][].
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 31.08.2019
 * @version 1.0
 */
public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;
    private int row;
    private int col;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (values.length > row) {
            if (values[row].length > col) {
                result = true;
                break;
            }
            col = 0;
            row++;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
           throw new NoSuchElementException();
        }
        return values[row][col++];
    }
}