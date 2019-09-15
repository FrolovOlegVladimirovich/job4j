package ru.job4j.map;

import java.util.*;

/**
 * Коллекция HashMap на базе ассоциативного массива.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.09.2019
 * @version 1.0
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node> {
    private Node<K, V>[] table;
    private int capacity = 16;
    private int counter;
    private int modCount;

    public SimpleHashMap() {
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    /**
     * Узел для хранения ключа и значения.
     * @param <K> Ключ.
     * @param <V> Значение.
     */
    class Node<K, V> {
        private final int hash;
        private final K key;
        private final V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{"
                    + "hash=" + hash
                    + ", key=" + key
                    + ", value=" + value
                    + '}';
        }
    }

    /**
     * Добавляет элемент в хеш-таблицу.
     * @param key Ключ.
     * @param value Значение.
     * @return true, если элемент успешно добавлен в хеш-таблицу.
     */
    public boolean insert(K key, V value) {
        int index = bucketNum(key);
        Node<K, V> node = table[index];
        if (node != null && Objects.equals(key, node.key)) {
            return false;
        }
        if (node == null) {
            counter++;
        }
        table[index] = new Node<>(hash(key), key, value);
        modCount++;
        loadFactor();
        return true;
    }

    /**
     * Возвращает значение элемента по ключу.
     * @param key Ключ.
     * @return Значение.
     */
    public V get(K key) {
        Node<K, V> node = table[bucketNum(key)];
        return node == null ? null : node.value;
    }

    /**
     * Удаляет элемент из хеш-таблицы по ключу.
     * @param key Ключ.
     * @return true, если элемент успешно удален из хеш-таблицы.
     */
    public boolean delete(K key) {
        int index = bucketNum(key);
        Node<K, V> node = table[index];
        if (node == null || !Objects.equals(key, node.key)) {
            return false;
        }
        table[index] = null;
        modCount++;
        counter--;
        return true;
    }

    /**
     * Вычисляет хеш-код для ключа, используемый в хеш-таблице.
     * @param key Ключ.
     * @return Хеш-код внутри хеш-таблицы, для доступа к ячейке table.
     */
    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    /**
     * Вычисляет номер корзины для хранения и доступа к элементу.
     * @param key Ключ.
     * @return Номер корзины в table.
     */
    private int bucketNum(K key) {
        return Math.abs(hash(key) % capacity);
    }

    /**
     * Количество элементов в хеш-таблице.
     * @return Количество элементов в хеш-таблице.
     */
    public int getSize() {
        return counter;
    }

    /**
     * Объем хеш-таблицы.
     * @return Размер хеш-таблицы.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Увеличение объема хеш-таблицы.
     */
    private void reSize() {
        Node<K, V>[] oldTbl = table;
        capacity = capacity * 2;
        table = (Node<K, V>[]) new Node[capacity];
        counter = 0;
        for (Node<K, V> node: oldTbl) {
            if (node != null) {
                insert(node.key, node.value);
            }
        }
        modCount++;
    }

    /**
     * Проверка заполнения хеш-таблицы.
     * Если размер достигает 75% от объема,
     * вызывается метод reSize().
     */
    private void loadFactor() {
        if (counter == (capacity * 0.75f)) {
            reSize();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }

    @Override
    public Iterator<SimpleHashMap.Node> iterator() {
        return new Iterator<>() {
            private int cursor;
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
            public SimpleHashMap.Node next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (cursor < table.length) {
                    if (table[cursor] != null) {
                        break;
                    }
                    cursor++;
                }
                elements--;
                return table[cursor++];
            }
        };
    }
}