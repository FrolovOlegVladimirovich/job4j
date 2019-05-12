package ru.job4j.condition;

/**
 * Нахождение максимального числа из трех через тернарный оператор.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 12.05.2019
 * @version 1.0
 */
public class MultiMax {
    public int max(int first, int second, int third) {
        int result = first;
        int maxFirstOrSecond = first > second ? first : second;
        return maxFirstOrSecond > third ? maxFirstOrSecond : third;
    }
}