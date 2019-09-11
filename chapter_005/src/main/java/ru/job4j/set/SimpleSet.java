package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Коллекция SimpleSet на базе массива внутри контейнера SimpleArrayList.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 11.09.2019
 * @version 1.0
 */
public class SimpleSet<E> implements Iterable<E> {
    private SimpleArrayList<E> container = new SimpleArrayList<>(100);

    void add(E e) {
        if (contains(e)) {
            container.add(e);
        }
    }

    /**
     * Проверяет уникальность добавляемого элемента.
     */
    private boolean contains(E e) {
        boolean result = true;
        for (E value : container) {
            if (Objects.equals(e, value)) {
                result = false;
                break;
            }
        }
    return result;
    }

    public int size() {
        return container.size();
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}