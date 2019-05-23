package ru.job4j.array;

/**
 * Поменять значения элементов массива в обратном порядке.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 17.05.2019
 * @version 1.0
 */
public class Turn {
    public int[] back(int[] array) {
        for (int i = 0, j = 1; i <= ((array.length / 2) - 1); i++, j++) {
            int temp = array[i];
            array[i] = array[array.length - j];
            array[array.length - j] = temp;
        }
        return array;
    }
}

