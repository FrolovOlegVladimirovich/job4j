package ru.job4j.array;

/**
 * Формирует таблицу умножения в виде двумерного массива.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.05.2019
 * @version 1.1
 */
public class Matrix {

    /**
     * @param size размер таблицы умножения / массива.
     * @return возвращает двумерный массив в виде таблицы умножения.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i != size; i++) {
            for (int j = 0; j != size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}