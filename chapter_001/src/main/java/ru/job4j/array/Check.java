package ru.job4j.array;

/**
 * Проверка всех элементов массива на значение true или false.
 *
 * Если все элементы массива равны true - результат true.
 * Если все элементы массива равны false - результат true.
 * Если значения элементов массива не одинаковы - результат  false.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 17.05.2019
 * @version 1.1
 */
public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        boolean first = data[0];
        for (int i = 1; i != data.length; i++) {

            if (data[i] != first) {
                result = false;
                break;
            }
        }
        return result;
    }
}