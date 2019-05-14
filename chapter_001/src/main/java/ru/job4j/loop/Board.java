package ru.job4j.loop;

/**
 * Рисуем шахматную доску заданного размера символами 'x' и ' '.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.05.2019
 * @version 1.0
 */
public class Board {

    /**
     * Рисуем шахматную доску с помощью циклов.
     *
     * Класс StringBuilder служит для построения строки.
     * Метод append добавляет символы и строки в некоторый буфер StringBuilder вместо вывода на консоль.
     *
     * Цикл с итератором i - строка, с итератором j - столбец.
     *
     * Высота доски достигается за счёт переходов на новую линию. В Windows это "\r\n", в Linux - "\n".
     * Универсальный вариант: System.getProperty("line.separator") - автоматически определяет переход в зависимости от ОС.
     *
     * screen.append(ln) - добавляет перевод на новую строку.
     *
     * @param width Ширина доски (столбец).
     * @param height Высота доски (строка).
     * @return Метод toString для объекта screen (StringBuilder) возвращает все добавленные в него символы и строки в одну строку.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("x");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}