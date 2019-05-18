package ru.job4j.array;

/**
 * Проверка всех элементов двухмерного массива на значение true или false по диагонали.
 *
 * Если все элементы массива равны по каждой диагонали true - результат true.
 * Если значения элементов массива по каждой диагонали не одинаковы - результат  false.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.05.2019
 * @version 1.1
 */
public class MatrixCheck {
    /**
     * Создается два одномерных массива diagonalLeft[] и diagonalRight[].
     *
     * diagonalLeft[] содержит значения по первой диагонали из матрицы data[][].
     * diagonalRight[] содержит значения по второй диагонали из матрицы data[][].
     *
     * Используется метод mono из класса Check для проверки всех значений каждой диагонали на true/false.
     *
     * @param data - двухмерный массив с булевыми значениями.
     * @return возвращает результат проверки двух диагоналей на true/false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;

        boolean[] diagonalLeft = new boolean[data.length];
        boolean[] diagonalRight = new boolean[data.length];

        for (int i = 0; i != data.length; i++) {
            diagonalLeft[i] = data[i][i];
        }
        int i = 0;
        int j = data.length - 1;
        for (; i != data.length && j >= 0; i++, j--) {
            diagonalRight[i] = data[i][j];
        }

        Check checkLeft = new Check();
        Check checkRight = new Check();

        boolean resultLeft = checkLeft.mono(diagonalLeft);
        boolean resultRight = checkRight.mono(diagonalRight);

        if (!resultLeft || !resultRight) {
            result = false;
        }
        return result;
    }
}