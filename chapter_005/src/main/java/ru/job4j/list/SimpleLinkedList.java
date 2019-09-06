package ru.job4j.list;

/**
 * Односвязный список.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.09.2019
 * @version 1.0
 */
public class SimpleLinkedList<E> {
    private int size;
    private Node<E> first;

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        private final E data;
        private Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    /**
     * Метод добавляет данные в начало списка.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Удаляет первый элемент в списке (с конца списка).
     */
    public E delete() {
        E result = this.first.data;
        this.first = this.first.next;
        size--;
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получает размер коллекции.
     */
    public int getSize() {
        return this.size;
    }
}