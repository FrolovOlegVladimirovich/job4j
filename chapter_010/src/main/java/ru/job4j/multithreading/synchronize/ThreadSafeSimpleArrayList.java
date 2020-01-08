package ru.job4j.multithreading.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

/**
 * A thread-safe dynamic array-based list.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
@ThreadSafe
public class ThreadSafeSimpleArrayList<E> implements Iterable<E> {
    @GuardedBy("this")
    private final SimpleArrayList<E> list;

    public ThreadSafeSimpleArrayList(SimpleArrayList<E> list) {
        this.list = list;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy().iterator();
    }

    private synchronized SimpleArrayList<E> copy() {
        var copy = new SimpleArrayList<E>();
        list.forEach(copy::add);
        return copy;
    }

    public synchronized void add(E value) {
        list.add(value);
    }

    public synchronized E get(int index) {
        return list.get(index);
    }

    public synchronized int size() {
        return list.size();
    }
}