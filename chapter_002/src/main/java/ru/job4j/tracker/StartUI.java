package ru.job4j.tracker;

/**
 * Точка входа в программу Tracker.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.06.2019
 * @version 3.1
 */
public class StartUI {
    private final Input input;
    private final Tracker tracker;

    /**
     * Конструктор класса с параметрами.
     *
     * @param input - интерфейс для ввода данных пользователем в консоль.
     * @param tracker - база заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основной цикл программы.
     *
     * Вывод меню в консоль и ввод пользователем пунктов меню.
     *
     */
    public void init() {
        int key;
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.setRanges();
            key = input.ask("Введите пункт меню: ", menu.getRanges());
            menu.select(key);
        }
        while (key != 6);
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}