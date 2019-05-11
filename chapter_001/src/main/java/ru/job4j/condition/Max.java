package ru.job4j.condition;

/**
 * Нахождение максимального числа из двух через тернарный оператор.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 11.05.2019
 * @version 1.0
 */
public class Max {
    public int max(int left, int right) {
        int result = left > right ? left : right;
        return result;
    }
}