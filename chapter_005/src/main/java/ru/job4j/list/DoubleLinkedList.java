package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Связный список LinkedList.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 10.09.2019
 * @version 1.0
 */
public class DoubleLinkedList<E> implements Iterable<E> {
    private int modCount;
    private int size;
    private Node<E> first;
    private Node<E> last;

    /**
     * Узел - элемент списка.
     * Содержит ссылки на предыдущий и следующий элементы.
     */
    private static class Node<E> {
        E data;
        private Node<E> previous;
        private Node<E> next;

        public Node(Node<E> previous, E data, Node<E> next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }

    public E getFirstData() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public E getLastData() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last.data;
    }

    public void deleteLastNode() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        last = last.previous;
        size--;
        modCount++;
        if (size == 0) {
            first = null;
        }
    }

    /**
     * Добавляет новый элемент.
     */
    public void add(E value) {
        Node<E> newNode = new Node<>(last, value, null);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        modCount++;
        size++;
    }

    /**
     * Получает элемент по индексу.
     */
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> result = first;
        for (int i = 0; i < size; i++) {
            if (index != i) {
                result = result.next;
            } else {
                break;
            }
        }
        return result.data;
    }

    /**
     * Возвращает количество элементов в списке.
     */
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> cursor = first;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = cursor.data;
                cursor = cursor.next;
                return result;
            }
        };
    }
}