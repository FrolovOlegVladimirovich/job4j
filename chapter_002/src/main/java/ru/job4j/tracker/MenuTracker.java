package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Реализация меню программы Tracker.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.06.2019
 * @version 1.1
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];
    private int position;

    /**
     * Конструктор
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Заполняет массив пунктами меню.
     */
    public void fillActions() {
        addAction(new CreateItem());
        addAction(new ShowAllItems());
        addAction(new EditItem());
        addAction(new DeleteItem());
        addAction(new FindItemById());
        addAction(new FindItemByName());
        addAction(new ExitProgram());
    }

    /**
     * Реализует заполнение массива пунктами меню.
     * @param action - пункт меню, реализующий действие.
     */
    private void addAction(UserAction action) {
        this.actions[this.position++] = action;
    }

    /**
     * Метод в зависимости от указанного ключа (пункта меню), выполняет соответствующее действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        if (key < actions.length) {
            this.actions[key].execute(this.input, this.tracker);
        } else {
            System.out.println("Пункт меню под номером " + key + " отсутствует!");
        }
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        System.out.println();
        System.out.println(" <<< Меню >>>");
        System.out.println();
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println();
    }

    /**
     * Добавление новой заявки в базу.
     */
    public class CreateItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------------------- Добавление новой заявки --------------------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("-------------------- Новая заявка с id: " + item.getId() + " --------------------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новую заявку.");
        }
    }

    /**
     * Выводит список всех заявок в базе.
     */
    public class ShowAllItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------------------- Список всех заявок --------------------");
            if (tracker.getPosition() != 0) {
                System.out.println(Arrays.toString(tracker.findAll()));
            } else {
                System.out.println("Заявки в базе отсутствуют!");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заявки.");
        }
    }

    /**
     * Редактирует заявку в базе, найденную по номеру id.
     */
    public class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------------------- Редактировать заявку --------------------");
            String id = input.ask("Введите номер редактируемой заявки: ");
            if (tracker.findById(id) != null) {
                System.out.println("ID заявки: " + tracker.findById(id).getId());
                System.out.println("Имя заявки: " + tracker.findById(id).getName());
                System.out.println("Описание заявки: " + tracker.findById(id).getDesc());
                System.out.println();
                String name = input.ask("Введите новое имя заявки: ");
                String desc = input.ask("Введите новое описание заявки: ");
                Item item = new Item(name, desc);
                item.setId(id);
                if (tracker.replace(id, item)) {
                    System.out.println("-------------------- Изменения в заявке " + tracker.findById(id).getId() + " сохранены --------------------");
                }
            } else {
                System.out.println("Заявка с номером " + id + " не найдена!");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Редактировать заявку.");
        }
    }

    /**
     * Удаляет заявку, найденную по номеру id.
     */
    public class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------------------- Удаление заявки --------------------");
            String id = input.ask("Введите номер заявки: ");
            if (tracker.findById(id) != null) {
                System.out.println("ID заявки: " + tracker.findById(id).getId());
                System.out.println("Имя заявки: " + tracker.findById(id).getName());
                System.out.println("Описание заявки: " + tracker.findById(id).getDesc());
                String question = input.ask("* Удалить заявку? * Введите любой символ для удаления заявки (N для отмены) :");
                if (question.equals("N")) {
                    System.out.println("-------------------- Удаление отменено --------------------");
                } else if (tracker.delete(id)) {
                    System.out.println("-------------------- Заявка " + id + " удалена --------------------");
                }
            } else {
                System.out.println("Заявка с номером " + id + " не найдена!");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удалить заявку.");
        }
    }

    /**
     * Поиск заявки по номеру id.
     */
    public class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------------------- Поиск по номеру заявки --------------------");
            String id = input.ask("Введите номер заявки: ");
            if (tracker.findById(id) != null) {
                System.out.println("ID заявки: " + tracker.findById(id).getId());
                System.out.println("Имя заявки: " + tracker.findById(id).getName());
                System.out.println("Описание заявки: " + tracker.findById(id).getDesc());
            } else {
                System.out.println("Заявка с номером " + id + " не найдена!");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по номеру ID.");
        }
    }

    /**
     * Поиск заявки по имени.
     */
    public class FindItemByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------------------- Поиск по имени заявки --------------------");
            String name = input.ask("Введите имя заявки: ");
            Item[] items = tracker.findByName(name);
            if (items.length != 0) {
                System.out.println(Arrays.toString(tracker.findByName(name)));
            } else {
                System.out.println("Заявка с именем " + name + " не найдена!");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по имени.");
        }
    }

    /**
     * Выход из программы.
     */
    public class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Закрыть программу.");
        }
    }
}
