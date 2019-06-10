package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Точка входа в программу Tracker.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.06.2019
 * @version 2.0
 */
public class StartUI {
    public static final String ADD = "0";
    public static final String SHOW_ALL = "1";
    public static final String EDIT = "2";
    public static final String DELETE = "3";
    public static final String FIND_BY_ID = "4";
    public static final String FIND_BY_NAME = "5";
    public static final String EXIT = "6";
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                showAllItems();
            } else if (EDIT.equals(answer)) {
                editItem();
            } else if (DELETE.equals(answer)) {
                deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                findById();
            } else if (FIND_BY_NAME.equals(answer)) {
                findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Добавление новой заявки в базу.
     */
    private void createItem() {
        System.out.println("-------------------- Добавление новой заявки --------------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("-------------------- Новая заявка с id: " + item.getId() + " --------------------");
    }

    /**
     * Выводит список всех заявок в базе.
     */
    private void showAllItems() {
        System.out.println("-------------------- Список всех заявок --------------------");
        if (this.tracker.getPosition() != 0) {
            System.out.println(Arrays.toString(this.tracker.findAll()));
        } else {
            System.out.println("Заявки в базе отсутствуют!");
        }
    }

    /**
     * Редактирует заявку в базе, найденную по номеру id.
     */
    private void editItem() {
        System.out.println("-------------------- Редактировать заявку --------------------");
        String id = this.input.ask("Введите номер редактируемой заявки: ");
        if (showItemParam(id)) {
            String name = this.input.ask("Введите новое имя заявки: ");
            String desc = this.input.ask("Введите новое описание заявки: ");
            Item item = new Item(name, desc);
            item.setId(id);
            if (this.tracker.replace(id, item)) {
                System.out.println("-------------------- Изменения в заявке " + this.tracker.findById(id).getId() +  " сохранены --------------------");
            }
        }
    }

    /**
     * Удаляет заявку, найденную по номеру id.
     */
    private void deleteItem() {
        System.out.println("-------------------- Удаление заявки --------------------");
        String id = this.input.ask("Введите номер заявки: ");
        if (showItemParam(id)) {
            String question = this.input.ask("* Удалить заявку? * Введите любой символ для удаления заявки (N для отмены) :");
            if (question.equals("N")) {
                System.out.println("-------------------- Удаление отменено --------------------");
            } else if (this.tracker.delete(id)) {
                System.out.println("-------------------- Заявка " + id + " удалена --------------------");
            }
        }
    }

    /**
     * Поиск заявки по номеру id.
     */
    private void findById() {
        System.out.println("-------------------- Поиск по номеру заявки --------------------");
        String id = this.input.ask("Введите номер заявки: ");
        showItemParam(id);
    }

    /**
     * Поиск заявки по имени.
     */
    private void findByName() {
        System.out.println("-------------------- Поиск по имени заявки --------------------");
        String name = this.input.ask("Введите имя заявки: ");
        Item[] items = this.tracker.findByName(name);
        if (items.length != 0) {
            System.out.println(Arrays.toString(this.tracker.findByName(name)));
        } else {
            System.out.println("Заявка с именем " + name + " не найдена!");
        }
    }

    /**
     * Выводит меню в консоль.
     */
    private void showMenu() {
        System.out.println();
        System.out.println(" <<< Меню >>>");
        System.out.println();
        System.out.println("0. Добавить новую заявку.");
        System.out.println("1. Показать все заявки.");
        System.out.println("2. Редактировать заявку.");
        System.out.println("3. Удалить заявку.");
        System.out.println("4. Найти заявку по номеру ID.");
        System.out.println("5. Найти заявку по имени.");
        System.out.println("6. Закрыть программу.");
        System.out.println();
    }

    /**
     * Выводит параметры заявки в консоль.
     *
     * @param id - уникальный номер заявки.
     */
    private boolean showItemParam(String id) {
        boolean find = false;
        if (this.tracker.findById(id) != null) {
            System.out.println("ID заявки: " + this.tracker.findById(id).getId());
            System.out.println("Имя заявки: " + this.tracker.findById(id).getName());
            System.out.println("Описание заявки: " + this.tracker.findById(id).getDesc());
            find = true;
        } else {
            System.out.println("Заявка с номером " + id + " не найдена!");
        }
        return find;
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}