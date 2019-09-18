package ru.job4j.tree;

import java.util.*;

/**
 * Tree, каждый элемент которого может содержать множество других дочерних элементов.
 *
 * @param <E>
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 19.09.2019
 * @version 1.0
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount;
    private int counter;

    /**
     * Конструктор присваивает значение корню дерева.
     * @param value Значение корня дерева.
     */
    public Tree(E value) {
        if (value == null) {
            throw new NullPointerException();
        }
        this.root = new Node<>(value);
        counter = 1;
    }

    /**
     * Конструктор создает пустное дерево.
     * Корень дерева == null.
     */
    public Tree() {
        counter = 0;
    }

    /**
     * Добавляет элемент child в элемент parent.
     *
     * Если child нет:
     * - если есть parent - добавляет child в parent;
     * - если нет parent - добавляет parent в корень дерева и добавляет child в parent.
     *
     * Если child есть:
     * - если есть parent - return false;
     * - если нет parent - добавляем parent в корень дерева.
     *
     * @param parent Элемент родитель.
     * @param child Дочерний элемент родителя.
     * @return false - если не добавлен ни parent, ни child. Иначе true.
     */
    @Override
    public boolean add(E parent, E child) {
        if (parent == null || child == null) {
            return false;
        }
        Optional<Node<E>> prnt = findBy(parent);
        Optional<Node<E>> chd = findBy(child);
        if (chd.isEmpty()) {
            if (prnt.isPresent()) {
                prnt.get().add(new Node<>(child));
                return trueResult();
            } else {
                Node<E> newPrnt = new Node<>(parent);
                newPrnt.add(new Node<>(child));
                ifRootNull(newPrnt);
                return trueResult();
            }
        } else {
            if (prnt.isPresent()) {
                return false;
            } else {
                Node<E> newPrnt = new Node<>(parent);
                ifRootNull(newPrnt);
                return trueResult();
            }
        }
    }

    /**
     * Проверяет на null корень (root) дерева.
     * Если корень null - присваивает root значение parent.
     * Если корень не null - добавляет в root значение parent.
     * @param newPrnt Новый элемент parent (добавляется в дерево).
     */
    private void ifRootNull(Node<E> newPrnt) {
        if (this.root != null) {
            this.root.add(newPrnt);
        } else {
            this.root = newPrnt;
        }
    }

    /**
     * Возвращает true, инкрементирует счетчики
     * modCount (счетчик изменений в дереве)
     * и
     * counter (счетчик количества элементов в дереве).
     *
     * @return true
     */
    private boolean trueResult() {
        modCount++;
        counter++;
        return true;
    }

    /**
     * Поиск элемента дерева по значению value.
     * Использует алгоритм поиска в ширину.
     *
     * @param value Значение искомого элемента.
     * @return Элемент-узел в обертке Optional, содержащий значение value,
     * либо пустой, если значение value не найдено в дереве.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        if (this.root != null) {
            data.offer(this.root);
            while (!data.isEmpty()) {
                Node<E> el = data.poll();
                if (el.eqValue(value)) {
                    rsl = Optional.of(el);
                    break;
                }
                for (Node<E> child : el.leaves()) {
                    data.offer(child);
                }
            }
        }
        return rsl;
    }

    /**
     * Возвращает все значения элементов дерева.
     * @return Все значения элементов дерева.
     */
    List<E> allElements() {
        Queue<Node<E>> data = new LinkedList<>();
        List<E> result = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            result.add(el.getValue());
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * Проверят дерево на бинарность.
     * @return true, если дерево бинарное, иначе false.
     */
    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            List<Node<E>> leaves = data.poll().leaves();
            if (leaves.size() > 2) {
                return false;
            } else {
                for (Node<E> child : leaves) {
                    data.offer(child);
                }
            }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Queue<Node<E>> data = new LinkedList<>();
            private int elements = counter;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return elements != 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (elements == counter) {
                    data.offer(root);
                }
                Node<E> result = data.poll();
                for (Node<E> child : result.leaves()) {
                    data.offer(child);
                }
                elements--;
                return result.getValue();
            }
        };
    }
}