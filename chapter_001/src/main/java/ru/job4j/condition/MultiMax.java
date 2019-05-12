package ru.job4j.condition;

/**
 * Нахождение максимального числа из трех через тернарный оператор.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 12.05.2019
 * @version 1.1
 */
public class MultiMax {
    public int max(int first, int second, int third) {
        int result = first > second ? first : second;
        return result > third ? result : third;
    }
}