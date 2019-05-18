package ru.job4j.array;

/**
 * Проверка всех элементов двухмерного массива на значение true или false по диагонали.
 *
 * Если все элементы массива равны по каждой диагонали true - результат true.
 * Если значения элементов массива по каждой диагонали не одинаковы - результат  false.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.05.2019
 * @version 1.2
 */
public class MatrixCheck {

    /**
     * @param data - двухмерный массив с булевыми значениями.
     * @return возвращает результат проверки двух диагоналей на true/false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        boolean resultLeft = true;
        boolean resultRight = true;
        boolean firstLeft = data[0][0];
        boolean firstRight = data[0][data.length - 1];
        int i = 1;
        int j = data.length - 2;
        for (; i != data.length && j >= 0; i++, j--) {
            if (data[i][i] != firstLeft) {
                resultLeft = false;
                break;
            }
            if (data[i][j] != firstRight) {
                resultRight = false;
                break;
            }
        }

        if (!resultLeft || !resultRight) {
            result = false;
        }

        return result;
    }
}