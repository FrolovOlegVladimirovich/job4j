package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Реализация меню программы Tracker.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.06.2019
 * @version 1.3
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];
    private int[] ranges = new int[this.actions.length];
    private int position;
    private StartUI ui;

    /**
     * Конструктор
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker, StartUI ui) {
        this.input = input;
        this.tracker = tracker;
        this.ui = ui;
    }

    /**
     * Заполняет массив пунктами меню.
     */
    public void fillActions() {
        addAction(new CreateItem(0, "Добавить новую заявку."));
        addAction(new ShowAllItems(1, "Показать все заявки."));
        addAction(new EditItem(2, "Редактировать заявку."));
        addAction(new DeleteItem(3, "Удалить заявку."));
        addAction(new FindItemById(4, "Найти заявку по номеру ID."));
        addAction(new FindItemByName(5, "Найти заявку по имени."));
        addAction(new ExitProgram(6, "Закрыть программу."));
    }

    /**
     * Реализует заполнение массива пунктами меню.
     *
     * @param action - пункт меню, реализующий действие.
     */
    private void addAction(BaseAction action) {
        this.actions[this.position++] = action;
    }

    /**
     * Метод в зависимости от указанного ключа (пункта меню), выполняет соответствующее действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
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
     * Сетер присваивает значения пунктов меню в массив ranges.
     * Массив ranges используется в качестве диапазона значений
     * для проверки введенных пользователем пунктов меню.
     */
    public void setRanges() {
        for (int i = 0; i != this.actions.length; i++) {
            this.ranges[i] = i;
        }
    }

    /**
     * Гетер получает массив с диапазоном значений пунктов меню.
     *
     * @return Массив.
     */
    public int[] getRanges() {
        return this.ranges;
    }

    /**
     * Добавление новой заявки в базу.
     */
    public class CreateItem extends BaseAction {
        public CreateItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * Выводит список всех заявок в базе.
     */
    public class ShowAllItems extends BaseAction {
        public ShowAllItems(int key, String name) {
            super(key, name);
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
    }

    /**
     * Редактирует заявку в базе, найденную по номеру id.
     */
    public class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * Удаляет заявку, найденную по номеру id.
     */
    public class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * Поиск заявки по номеру id.
     */
    public class FindItemById extends BaseAction {
        public FindItemById(int key, String name) {
            super(key, name);
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
    }

    /**
     * Поиск заявки по имени.
     */
    public class FindItemByName extends BaseAction {
        public FindItemByName(int key, String name) {
            super(key, name);
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
    }

    /**
     * Выход из программы.
     */
    public class ExitProgram extends BaseAction {
        public ExitProgram(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            ui.stop();
        }
    }
}
