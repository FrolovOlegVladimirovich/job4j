package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Node - узел для хранения данных в дереве.
 *
 * Содержит List дочерних элементов и value - значение элемента parent.
 *
 * @param <E>
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 19.09.2019
 * @version 1.0
 */
public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void add(Node<E> child) {
        if (child != null) {
            this.children.add(child);
        }
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}