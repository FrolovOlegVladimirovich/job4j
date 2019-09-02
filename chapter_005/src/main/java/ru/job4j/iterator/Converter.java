package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Конвертер итератора множества итераторов целых чисел в один итератор целых чисел.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.09.2019
 * @version 1.0
 */
public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iterator) {
        return new Iterator<>() {
            Iterator<Integer> innerIterator = iterator.next();

            @Override
            public boolean hasNext() {
                while (iterator.hasNext() && !innerIterator.hasNext()) {
                    innerIterator = iterator.next();
                }
                return innerIterator.hasNext();
            }

            @Override
            public Integer next() throws NoSuchElementException {
                if (!innerIterator.hasNext()) {
                    if (!iterator.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    innerIterator = iterator.next();
                }
                return innerIterator.next();
            }
        };
    }
}