package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Компаратор для строк.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.08.2019
 * @version 1.0
 */
public class StringsCompare implements Comparator<String> {
    /**
     * Сравнивает два слова в алфавитном порядке.
     *
     * @param left Первое слово в сравнении.
     * @param right Второе слово в сравнении.
     * @return 0, если слова одинаковые;
     * -1, если первое слово должно быть первым по порядку;
     * 1, если второе слово должно быть первым по порядку.
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int length = left.length() > right.length() ? right.length() : left.length();
        char[] leftCh = left.toCharArray();
        char[] rightCh = right.toCharArray();
        for (int i = 0; i < length; i++) {
            result = Character.compare(leftCh[i], rightCh[i]);
            if (result != 0) {
                break;
            }
        }
        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}
