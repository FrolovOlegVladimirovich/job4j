package ru.job4j.loop;

/**
 * Рисуем пирамиду заданной высоты символами '^' и ' '.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.05.2019
 * @version 1.0
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
     */
    public String rightTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int width = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Рисуем левую часть пирамиды с помощью циклов (по аналогии с правой частью).
     *
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int width = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= width - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Рисуем целую пирамиду.
     *
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int width = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
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