package ru.job4j.array;

/**
 * Поиск элемента в массиве и вывод результата в виде индекса найденного элемента массива.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 17.05.2019
 * @version 1.0
 */
public class FindLoop {

    /**
     * Поиск элемента массива через перебор индексов циклом for.
     * @param data массив, в котором ищем.
     * @param element значение, которое ищем в массиве.
     * @result индекс элемента массива data, найденный по заданному значению element.
     */
    public static int indexOf(int[] data, int element) {
        int result = -1;
        for (int i = 0; i != data.length; i++) {
            if (data[i] == element) {
                result = i;
                break;
            }
        }
        return result;
    }
}