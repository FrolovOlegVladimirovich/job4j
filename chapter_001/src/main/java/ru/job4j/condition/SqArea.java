package ru.job4j.condition;

/**
 * Вычисление площади прямоугольника при известных значениях периметра и значения разницы между шириной и высотой.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.05.2019
 * @version 1.0
 */

public class SqArea {

    /**
     * Метод для вычисления площади прямоугольника при известных значениях периметра и значения разницы между шириной и высотой.
     * @param p значение периметра прямоугольника.
     * @param k - значение: ширина больше высоты в k раз.
     * @return Площадь прямоугольника.
     */
    public static int square(int p, int k) {
        int h = (p / 2) / (k + 1);
        int w = h * k;
        return h * w;
    }
}