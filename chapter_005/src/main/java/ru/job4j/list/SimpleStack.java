package ru.job4j.list;

/**
 * Стэк на базе связного списка.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 10.09.2019
 * @version 1.0
 */
public class SimpleStack<T> {
    private DoubleLinkedList<T> container;

    public SimpleStack() {
        this.container = new DoubleLinkedList<>();
    }

    /**
     * Возвращает значение последнего элемента и удаляет его из стэка.
     */
    public T poll() {
        final T result = container.getLastData();
        container.deleteLastNode();
        return result;
    }

    /**
     * Добавляет элемент в стэк.
     */
    public void push(T value) {
    container.add(value);
    }

    /**
     * Размер стэка.
     */
    public int getSize() {
        return container.getSize();
    }
}