package ru.job4j.array;

import java.util.Arrays;

/**
 * Удаление дубликатов в массиве.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.05.2019
 * @version 2.1
 */
public class ArrayDuplicate {

    /**
     * Метод сравнивает каждый элемент массива по-порядку со следующим по индексу элементом.
     * Если элементы одинаковы, то бОльший по индексу дублирующий элемент меняется местами с последним элементом массива.
     *
     * Каждый дублирующий элемент помещается в конце массива перед другими ранее добавленными дублирующими элементами.
     *
     * last - счетчик дубликатов по индексам массива:
     * дублирующие элементы массива, добавленные в конце массива не участвую в сравнении.
     *
     * @param array - массив из строковых элементов.
     * @return возвращает массив из строковых элементов, очищенный от дубликатов.
     */
    public String[] remove(String[] array) {
        int last = 0;
        for (int i = 0; i < array.length - last; i++) {
            for (int j = array.length - 1 - last; j != i; j--) {

                if (array[i].equals(array[j])) {
                        String tmp = array[j];
                        array[j] = array[array.length - 1 - last];
                        array[array.length - 1 - last] = tmp;
                        last++;
                    }
                }
            }
        array = Arrays.copyOf(array, array.length - last);
        return array;
    }
}