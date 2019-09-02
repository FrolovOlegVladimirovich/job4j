package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор четных чисел для массива int[].
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.09.2019
 * @version 1.0
 */
public class EvenIterator implements Iterator<Integer> {
    private final int[] values;
    private int countArray;

    public EvenIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (values.length > countArray) {
            if (values[countArray] % 2 == 0) {
                result = true;
                break;
            } else {
                countArray++;
            }
        }
        return result;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return values[countArray++];
    }
}