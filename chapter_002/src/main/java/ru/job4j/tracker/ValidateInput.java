package ru.job4j.tracker;

import java.util.List;

/**
 * Наследник класса ConsoleInput, переопределяющий метод ask.
 * Реализует проверку введенных пользователем данных на наличие ошибок.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 17.06.2019
 * @version 1.0
 */
public class ValidateInput implements Input {
    private final Input input;

    /**
     * Конструктор принимает объект типа Input для реализации ConsoleInput и StubInput.
     * @param input - объект интерфейса ввода пользовательских данных из консоли.
     */
    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Метод, проверяющий введенное пользователем значение на начличие исключений/ошибок.
     *
     * @param question - запрос пользователю.
     * @param range - List с диапазоном принимаемых значений от пользователя.
     * @return Значение в формате int, прошедшее проверку на наличие исключений.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            }           catch (MenuOutException moe) {
                System.out.println("Данный пункт меню отсутствует! Введите корректный пункт меню: ");
            } catch (NumberFormatException nfe) {
                System.out.println("Ошибка! Введите номер меню в правильном формате: ");
            }
        } while (invalid);
        return value;
    }
}
