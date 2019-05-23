package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Рисуем пирамиду заданной высоты символами '^' и ' '.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.05.2019
 * @version 2.0
 */
public class Paint {

    /**
     * Рисуем правую часть пирамиды с помощью циклов.
     *
     * StringBuilder screen - буфер для результата
     * Цикл с итератором row - строка, с итератором column - столбец.
     * screen.append(System.lineSeparator()) - перевод на новую строку.
     *
     * @param height Высота пирамиды.
     * @return Метод toString возвращает из объекта screen класса StringBuilder все добавленные в него символы и строки в одну строку.
     * В результате получаем нарисованную правую часть пирамиды.
     *
     * С помощью метода loopBy убираем лишнюю часть кода:
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Рисуем левую часть пирамиды с помощью циклов (по аналогии с правой частью).
     *
     * С помощью метода loopBy убираем лишнюю часть кода:
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Рисуем целую пирамиду.
     *
     * С помощью метода loopBy убираем лишнюю часть кода:
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Метод для рефакторинга кода (с помощью данного метода убираем повторяющийся код в классе Paint.java).
     * C использованием Generics и Lambda.
     *
     * @param height Высота пирамиды.
     * @param height Ширина пирамиды/строки.
     * @return Метод toString возвращает из объекта screen класса StringBuilder все добавленные в него символы и строки в одну строку.
     * В результате получаем нарисованную правую часть пирамиды.
     */
    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
