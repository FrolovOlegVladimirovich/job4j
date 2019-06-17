package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Ввод пользовательских данных из консоли.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.06.2019
 * @version 2.0
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Ввод пользовательских данных из консоли.
     *
     * @param question - запрос пользователю с выводом в консоль.
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Получение значения типа int, введенного пользователем.
     * Метод проверяет полученное от пользователя значение на наличие исключений/ошибок.
     * @param question - запрос пользователю.
     * @param range - массив с диапазоном принимаемых значений от пользователя.
     * @return Значение типа int.
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Выход из диапазона заданных значений!");
        }
    }
}
