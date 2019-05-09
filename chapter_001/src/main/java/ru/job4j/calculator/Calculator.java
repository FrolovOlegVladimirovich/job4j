package ru.job4j.calculator;
/**
 * Класс для простейшего калькулятора (функции сложения, вычитания, деления, умножения).
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.05.2019
 * @version 1.0
 */
public class Calculator {
    public double add(double first, double second) { // сложение
        return first + second;
    }
    public double subtract(double first, double second) { // вычитание
        return first - second;
    }
    public double div(double first, double second) { // деление
        return first / second;
    }
    public double multiple(double first, double second) { // умножение
        return first * second;
    }
}