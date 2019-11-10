package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Реализация меню программы Tracker.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.06.2019
 * @version 1.3
 */
public class MenuTracker {
    private Input input;
    private ITracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private List<Integer> ranges = new ArrayList<>();
    private int position;
    private StartUI ui;
    private final Consumer<String> output;

    /**
     * Конструктор
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, ITracker tracker, Consumer<String> output, StartUI ui) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
        this.ui = ui;
    }

    /**
     * Заполняет ArrayList пунктами меню.
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
     * Реализует заполнение ArrayList пунктами меню.
     *
     * @param action - пункт меню, реализующий действие.
     */
    private void addAction(BaseAction action) {
        this.actions.add(this.position++, action);
    }

    /**
     * Метод в зависимости от указанного ключа (пункта меню), выполняет соответствующее действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        output.accept("");
        output.accept(" <<< Меню >>>");
        output.accept("");
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
        output.accept("");
    }

    /**
     * Сетер присваивает значения пунктов меню в ArrayList ranges.
     * ArrayList ranges используется в качестве диапазона значений
     * для проверки введенных пользователем пунктов меню.
     */
    public void setRanges() {
        for (int i = 0; i != this.actions.size(); i++) {
            this.ranges.add(i);
        }
    }

    /**
     * Гетер получает List с диапазоном значений пунктов меню.
     *
     * @return ArrayList.
     */
    public List<Integer> getRanges() {
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
        public void execute(Input input, ITracker tracker) {
            output.accept("-------------------- Добавление новой заявки --------------------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept("-------------------- Новая заявка с id: " + item.getId() + " --------------------");
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
        public void execute(Input input, ITracker tracker) {
            output.accept("-------------------- Список всех заявок --------------------");
            if (tracker.getPosition() != 0) {
                output.accept(tracker.findAll().toString());
            } else {
                output.accept("Заявки в базе отсутствуют!");
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
        public void execute(Input input, ITracker tracker) {
            output.accept("-------------------- Редактировать заявку --------------------");
            String id = input.ask("Введите номер редактируемой заявки: ");
            if (tracker.findById(id) != null) {
                output.accept("ID заявки: " + tracker.findById(id).getId());
                output.accept("Имя заявки: " + tracker.findById(id).getName());
                output.accept("Описание заявки: " + tracker.findById(id).getDesc());
                output.accept("");
                String name = input.ask("Введите новое имя заявки: ");
                String desc = input.ask("Введите новое описание заявки: ");
                Item item = new Item(name, desc);
                item.setId(id);
                if (tracker.replace(id, item)) {
                    output.accept("-------------------- Изменения в заявке " + tracker.findById(id).getId() + " сохранены --------------------");
                }
            } else {
                output.accept("Заявка с номером " + id + " не найдена!");
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
        public void execute(Input input, ITracker tracker) {
            output.accept("-------------------- Удаление заявки --------------------");
            String id = input.ask("Введите номер заявки: ");
            if (tracker.findById(id) != null) {
                output.accept("ID заявки: " + tracker.findById(id).getId());
                output.accept("Имя заявки: " + tracker.findById(id).getName());
                output.accept("Описание заявки: " + tracker.findById(id).getDesc());
                String question = input.ask("* Удалить заявку? * Введите любой символ для удаления заявки (N для отмены) :");
                if (question.equals("N")) {
                    output.accept("-------------------- Удаление отменено --------------------");
                } else if (tracker.delete(id)) {
                    output.accept("-------------------- Заявка " + id + " удалена --------------------");
                }
            } else {
                output.accept("Заявка с номером " + id + " не найдена!");
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
        public void execute(Input input, ITracker tracker) {
            output.accept("-------------------- Поиск по номеру заявки --------------------");
            String id = input.ask("Введите номер заявки: ");
            if (tracker.findById(id) != null) {
                output.accept("ID заявки: " + tracker.findById(id).getId());
                output.accept("Имя заявки: " + tracker.findById(id).getName());
                output.accept("Описание заявки: " + tracker.findById(id).getDesc());
            } else {
                output.accept("Заявка с номером " + id + " не найдена!");
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
        public void execute(Input input, ITracker tracker) {
            output.accept("-------------------- Поиск по имени заявки --------------------");
            String name = input.ask("Введите имя заявки: ");
            List<Item> items = new ArrayList<>(tracker.findByName(name));
            if (items.size() != 0) {
                output.accept(tracker.findByName(name).toString());
            } else {
                output.accept("Заявка с именем " + name + " не найдена!");
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

        public void execute(Input input, ITracker tracker) {
            ui.stop();
        }
    }
}