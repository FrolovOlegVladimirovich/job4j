package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Преобразование матрицы чисел в список чисел.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 21.08.2019
 * @version 1.0
 */
public class Converter {
    public List<Integer> convertMatrixListToList(List<List<Integer>> matrix) {
        return matrix.stream().flatMap(num -> num.stream()).collect(Collectors.toList());
    }
}