package ru.job4j.array;

/**
 * Заполнение массива числами, возведенными в квадрат.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 16.05.2019
 * @version 1.0
 */
public class Square {

    /**
     * Заполнение массива числами от 1 до bound, возведенными в квадрат.
     * @param bound количество элементов массива result
     */
    public int[] calulate(int bound) {
        int[] result = new int[bound];
        for (int i = 0; i != bound; i++) {
            result[i] = (int) Math.pow(i + 1, 2);
        }
        return result;
    }
}

