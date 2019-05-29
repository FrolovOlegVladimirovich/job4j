package ru.job4j.condition;

/**
 * Нахождение максимального числа из двух через тернарный оператор.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 11.05.2019
 * @version 2.0
 */
public class Max {
    public int max(int one, int two) {
        return one > two ? one : two;
    }

    public int max(int one, int two, int three) {
        return (max(one, max(two, three)));
    }

    public int max(int one, int two, int three, int four) {
        return max(one, max(two, three, four));
    }
}