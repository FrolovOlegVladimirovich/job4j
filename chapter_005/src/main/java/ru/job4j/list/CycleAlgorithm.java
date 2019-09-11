package ru.job4j.list;

/**
 * Определение цикличности в односвязном списке.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 10.09.2019
 * @version 1.0
 */
public class CycleAlgorithm {

    public static class Node<E> {
        final E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    static boolean hasCycle(Node first) {
        boolean result = false;
        Node current = first;
        Node next = current.next.next;
        while (next != null) {
            if (current == next) {
                result = true;
                break;
            } else {
                current = current.next;
                next = next.next.next;
            }
        }
    return result;
    }
}