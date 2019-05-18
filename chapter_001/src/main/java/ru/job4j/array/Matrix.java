package ru.job4j.array;

/**
 * Формирует таблицу умножения в виде двумерного массива.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.05.2019
 * @version 1.0
 */
public class Matrix {

    /**
     * @param size размер таблицы умножения / массива.
     * @result возвращает двумерный массив в виде таблицы умножения.
     */
    public int[][] multiple(int size) {

        int[][] table = new int[size][size];
        int counteri = 0;

        for (int i = 0; i != size; i++) {
            ++counteri;
            int counterj = 0;

            for (int j = 0; j != size; j++) {
                table[i][j] = (counteri) * (++counterj);
            }
        }
        return table;
    }
}