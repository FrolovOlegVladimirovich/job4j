package ru.job4j.stream;

import java.util.Arrays;

/**
 * Тестовое задание:
 *
 * Задан массив чисел.
 * 1. Нужно его отфильтровать, оставить только четные числа.
 * 2. Каждое число возвести в квадрат.
 * 3. И все элементы просуммировать.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 30.08.2019
 * @version 1.0
 *
 */
public class ArrayFilter {

    /**
     * Фильтрует массив, оставляя четные числа,
     * возводит каждое число в квадрат и
     * суммирует все числа.
     * @param array Массив int.
     * @return Сумма всех четных чисел, возведенных в квдрат.
     */
    public int evenSquaredSum(int[] array) {
        return Arrays.stream(array).
                filter(num -> num % 2 == 0).
                map(num -> num * num).
                reduce(0, Integer::sum);
    }
}