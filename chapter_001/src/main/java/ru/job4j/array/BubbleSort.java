package ru.job4j.array;

/**
 * Сортирует массив методом перестановки.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 17.05.2019
 * @version 1.0
 */
public class BubbleSort {
    public int[] sort(int[] array) {
        for (int i = 1; (array.length - i) != 1; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
}