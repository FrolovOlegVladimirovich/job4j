package ru.job4j.tracker;

/**
 * Точка входа в программу Tracker.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.06.2019
 * @version 3.2
 */
public class StartUI {
    private final Input input;
    private final Tracker tracker;
    private boolean exit = true;

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
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this);
        menu.fillActions();
        do {
            menu.show();
            menu.setRanges();
            menu.select(input.ask("Введите пункт меню: ", menu.getRanges()));
        }
        while (exit);
    }

    /**
     * Меняет флаг в переменной для выхода из цикла в init().
     * Реализует выход из программы.
     */
    public void stop() {
        this.exit = false;
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}