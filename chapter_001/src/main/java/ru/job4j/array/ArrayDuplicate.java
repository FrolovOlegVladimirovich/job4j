package ru.job4j.array;

import java.util.Arrays;

/**
 * Удаление дубликатов в массиве.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.05.2019
 * @version 1.0
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
        int last = 1;

        for (int i = 0; i != array.length; i++) {
            int j = 1 + i;

            for (; j <= (array.length - last); j++) {

                if (array[i].equals(array[j])) {
                    String tmp = array[j];
                    array[j] = array[array.length - last];
                    array[array.length - last] = tmp;
                    last++;
                }
            }
        }
        array = Arrays.copyOf(array, (array.length - (last - 1)));
        return array;
    }
}