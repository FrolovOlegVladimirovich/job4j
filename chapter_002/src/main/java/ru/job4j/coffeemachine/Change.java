package ru.job4j.coffeemachine;

import java.util.Arrays;

/**
 * Задание кофемашина: реализовать метод changes для выдачи сдачи автоматом.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.07.2019
 * @version 2.0
 */
public class Change {
    private final int[] coins = {10, 5, 2, 1};

    /**
     * Возвращает сдачу в виде массива с монетами разного номинала.
     *
     * @param value - купюра для покупки напитка.
     * @param price - цена напитка.
     * @return сдача - массив с монетами разного номинала.
     */
    public int[] changes(int value, int price) {
        int[] result = new int[30];
        int change = value - price;
        int resultIndex = 0;
        for (int i = 0; i != coins.length; i++) {
            int num = change / coins[i];
            change = change - num * coins[i];
            while (num != 0) {
                result[resultIndex++] = coins[i];
                num--;
            }
        }
        return Arrays.copyOf(result, resultIndex);
    }
}
