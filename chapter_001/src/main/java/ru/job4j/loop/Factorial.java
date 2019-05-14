package ru.job4j.loop;

/**
 * Вычисление факториала.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.05.2019
 * @version 1.0
 */
public class Factorial {
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}