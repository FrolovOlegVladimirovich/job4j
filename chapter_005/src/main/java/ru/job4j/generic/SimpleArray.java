package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Универсальная обертка над массивом.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 03.09.2019
 * @version 1.0
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] collection;
    private int counterPosition = 0;

    public SimpleArray(int size) {
        this.collection = new Object[size];
    }

    /**
     * Добавляет элемент в первую свободную ячейку.
     * @param model Элемент.
     * @throws RuntimeException
     */
    public void add(T model) throws RuntimeException {
        if (counterPosition >= collection.length) {
            throw new RuntimeException();
        }
        this.collection[counterPosition++] = model;
    }

    /**
     * Заменяет указанным элементом model элемент, находящийся по индексу index.
     * @param index Индекс.
     * @param model Элемент.
     * @throws NoSuchElementException
     */
    public void set(int index, T model) throws NoSuchElementException {
        noElementException(index);
        this.collection[index] = model;
    }

    /**
     * Возвращает элемент, расположенный по указанному индексу.
     * @param index Индекс.
     * @return Элемент типа T.
     * @throws NoSuchElementException
     */
    public T get(int index) throws NoSuchElementException {
        noElementException(index);
        return (T) this.collection[index];
    }

    /**
     * Удаляет элемент по указанному индексу, со сдвигом находящихся справа элементов.
     * @param index Индекс.
     * @throws NoSuchElementException
     */
    public void remove(int index) throws NoSuchElementException {
        noElementException(index);
        Object[] newCollection = new Object[collection.length - 1];
        System.arraycopy(collection, 0, newCollection, 0, index);
        System.arraycopy(collection, index + 1, newCollection, index, collection.length - index - 1);
        collection = new Object[collection.length - 1];
        collection = newCollection;
    }

    /**
     * Сверяет индекс элемента с диапазоном индексов контейнера.
     * @param index Индекс.
     * @throws NoSuchElementException
     */
    private void noElementException(int index) throws NoSuchElementException {
        if (index > collection.length - 1 || index < 0) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != collection.length;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (cursor == collection.length) {
                    throw new NoSuchElementException();
                }
                return (T) collection[cursor++];
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return Arrays.equals(collection, that.collection);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(collection);
    }
}