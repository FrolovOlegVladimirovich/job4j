package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертация в двухмерного массива в ArrayList.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.08.2019
 * @version 1.0
 */
public class ConvertMatrix2List {

    /**
     * Метод конвертирует двумерный массив в ArrayList.
     *
     * @param array Двумерный массив.
     * @return ArrayList из значений двумерного массива array.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] i : array) {
            for (int j: i) {
                result.add(j);
            }
        }
        return result;
    }
}