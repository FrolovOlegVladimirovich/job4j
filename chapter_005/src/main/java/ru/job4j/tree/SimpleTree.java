package ru.job4j.tree;

import java.util.Optional;

/**
 * Интерфейс структуры данных Tree.
 *
 * @param <E>
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 19.09.2019
 * @version 1.0
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return true, если элемент успешно добавлен в дерево.
     */
    boolean add(E parent, E child);

    /**
     * Поиск значения value в дереве.
     * @param value Значение искомого элемента.
     * @return Элемент-узел в обертке Optional, содержащий значение value,
     * либо пустой, если значение value не найдено в дереве.
     */
    Optional<Node<E>> findBy(E value);
}