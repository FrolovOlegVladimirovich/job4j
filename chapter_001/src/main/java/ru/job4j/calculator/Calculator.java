package ru.job4j.calculator;
/**
 * Класс для простейшего калькулятора (функции сложения, вычитания, деления, умножения).
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.05.2019
 * @version 1.0
 */
public class Calculator {

    /**
     * Сложение.
     * @param first Число №1.
     * @param second Число №2.
     * @return Результат сложения числа №1 и числа №2.
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     * Вычитание.
     * @param first Число №1.
     * @param second Число №2.
     * @return Результат разности числа №1 и числа №2.
     */
    public double subtract(double first, double second) {
        return first - second;
    }

    /**
     * Деление.
     * @param first Число №1.
     * @param second Число №2.
     * @return Результат деления числа №1 на число №2.
     */
    public double div(double first, double second) {
        return first / second;
    }

    /**
     * Умножение.
     * @param first Число №1.
     * @param second Число №2.
     * @return Результат умножения числа №1 на число №2.
     */
    public double multiple(double first, double second) {
        return first * second;
    }
}