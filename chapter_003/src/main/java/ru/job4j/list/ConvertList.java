package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертер ArrayList в различные типы данных.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.08.2019
 * @version 1.0
 */
public class ConvertList {

    /**
     * Конвертация ArrayList в двухмерный массив.
     *
     * Метод равномерно разделяет list на количество строк двухмерного массива rows,
     * заполняя оставшиеся пустые значения нулями.
     *
     * @param list Лист со значениями типа Integer.
     * @param rows Количество строк, на которые должен быть разделен list.
     * @return Двухмерный массив из значений list, разбитого на количество строк rows.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows;
        if (cells != 0) {
            cells = list.size() / rows + 1;
        } else {
            cells = list.size() / rows;
        }
        int[][] result = new int[rows][cells];

        int iRow = 0;
        int iCell = 0;
        for (Integer index : list) {
            if (iCell != cells) {
                result[iRow][iCell++] = index;
            } else {
                iRow++;
                iCell = 0;
                result[iRow][iCell++] = index;
            }
        }
        return result;
    }

    /**
     * Конвертация ArrayList int[] в ArrayList Integer.
     *
     * @param list Коллекция содержит int[].
     * @return ArrayList Integer.
     */
    public List<Integer> toList(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int element : array) {
                result.add(element);
            }
        }
        return result;
    }
}
