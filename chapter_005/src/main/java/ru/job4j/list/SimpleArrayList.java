package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Динамический список на базе массива.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.09.2019
 * @version 1.0
 */
public class SimpleArrayList<E> implements Iterable<E> {
    Object[] container;
    private int counter = 0;
    private int modCount = 0;

    public SimpleArrayList() {
        this.container = new Object[1];
    }

    public SimpleArrayList(int size) {
        this.container = new Object[size];
    }

    /**
     * Добавляет элемент в контейнер.
     */
    public void add(E value) {
        increaseCheck();
        container[counter++] = value;
        modCount++;
    }

    /**
     * Проверка размера и увеличение контейнера.
     */
    private void increaseCheck() {
        if (counter >= container.length) {
            container = Arrays.copyOf(container, container.length + 1);
        }
    }

    /**
     * Возвращает элемент из контейнера по индексу.
     */
    public E get(int index) {
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < counter;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[cursor++];
            }
        };
    }
}