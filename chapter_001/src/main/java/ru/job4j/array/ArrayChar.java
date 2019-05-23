package ru.job4j.array;

/**
 * Проверяет, начинается ли слово с определенной последовательности симоволов.
 *
 * Обертка над строкой.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 17.05.2019
 * @version 1.0
 */
public class ArrayChar {
    private char[] data;

    /**
     * Преобразуем строку в массив символов через toCharArray() и присваеваем char[] data.
     * @param line - строка текста.
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет, что солово начинается с префикса.
     * @param prefix - префикс.
     * @return если слово начинается с префикса == true.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0; i != value.length; i++) {
            if (value[i] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
