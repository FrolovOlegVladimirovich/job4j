package ru.job4j.tracker;

/**
 * Наследник класса ConsoleInput, переопределяющий метод ask.
 *
 *  * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 *  * @since 17.06.2019
 *  * @version 1.0
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Метод, проверяющий введенное пользователем значение на начличие исключений/ошибок.
     *
     * @param question - запрос пользователю.
     * @param range - массив с диапазоном принимаемых значений от пользователя.
     * @return Значение в формате int, прошедшее проверку на наличие исключений.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
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
