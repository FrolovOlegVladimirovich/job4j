package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Задание: Реализовать метод подсчета функции в диапазоне.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class RangeFunctionCount {

    /**
     * Метод производит расчет, заданный Function<Double, Double> в диапазоне значений от start до end.
     *
     * @param start Изначальное значение диапазона.
     * @param end Конечное значение диапазона.
     * @param func Функция, реализующая расчет.
     * @return List расчитанных значений.
     */
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i != end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}