package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Ввод пользовательских данных из консоли.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.06.2019
 * @version 1.0
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
}
