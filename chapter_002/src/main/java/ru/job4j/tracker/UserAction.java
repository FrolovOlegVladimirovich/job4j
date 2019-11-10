package ru.job4j.tracker;

/**
 * Интерфейс, реализующий действия в трекере.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.06.2019
 * @version 1.0
 */
public interface UserAction {

    /**
     * Метод возвращает ключ опции.
     * @return ключ
     */
    int key();

    /**
     * Основной метод.
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    void execute(Input input, ITracker tracker);

    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
    String info();
}
