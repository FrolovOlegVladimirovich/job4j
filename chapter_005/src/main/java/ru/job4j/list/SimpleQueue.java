package ru.job4j.list;

/**
 * Очередь на двух стеках.
 *
 * queue - стек используется для добавления элементов в очередь.
 * dequeue - стек для получения элементов из очереди.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 10.09.2019
 * @version 1.0
 */
public class SimpleQueue<T> {
    private SimpleStack<T> queue = new SimpleStack<>();
    private SimpleStack<T> dequeue = new SimpleStack<>();

    /**
     * Добавляет элемент в очередь (стек queue).
     */
    public void push(T value) {
        queue.push(value);
    }

    /**
     * Возвращает значение первого элемента в очереди и удаляет его из стека dequeue.
     *
     * Если все элементы из очереди dequeue были получены/удалены, то все элементы,
     * добавленные в стек queue, переносятся в стек dequeue в обратном порядке.
     */
    public T poll() {
        if (dequeue.getSize() == 0) {
            while (queue.getSize() != 0) {
                dequeue.push(queue.poll());
            }
        }
        return dequeue.poll();
    }
}