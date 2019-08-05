package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final StringBuilder menu =   new StringBuilder()
            .append(System.lineSeparator())
            .append(" <<< Меню >>>")
            .append(System.lineSeparator())
            .append(System.lineSeparator())
            .append("0. Добавить новую заявку.")
            .append(System.lineSeparator())
            .append("1. Показать все заявки.")
            .append(System.lineSeparator())
            .append("2. Редактировать заявку.")
            .append(System.lineSeparator())
            .append("3. Удалить заявку.")
            .append(System.lineSeparator())
            .append("4. Найти заявку по номеру ID.")
            .append(System.lineSeparator())
            .append("5. Найти заявку по имени.")
            .append(System.lineSeparator())
            .append("6. Закрыть программу.")
            .append(System.lineSeparator())
            .append(System.lineSeparator());
    private final Tracker tracker = new Tracker();
    private final Item item1 = new Item("test name", "desc 1");
    private  final Item item2 = new Item("test name", "desc 2");

    /**
     * Добавляет две заявки в трекер.
     */
    @Before
    public void addNewTracker() {
        tracker.add(item1);
        tracker.add(item2);
    }

    /**
     * Заменяем стандартный вывод на вывод в память для тестирования.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Возвращаем обратно стандартный вывод в консоль.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new ArrayList<>(Arrays.asList("0", "test name", "test desc", "6")));
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Input input = new StubInput(new ArrayList<>(Arrays.asList("2", item1.getId(), "new test name", "new desc", "6")));
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item1.getId()).getName(), is("new test name"));
    }

    @Test
    public void whenDeleteItem() {
        Input input = new StubInput(new ArrayList<>(Arrays.asList("3", item1.getId(), "", "6")));
        new StartUI(input, tracker).init();
        Item result = null;
        assertThat(tracker.findById(item1.getId()), is(result));
    }

    @Test
    public void whenCancelDeleteThenTrackerNoDelete() {
        Input input = new StubInput(new ArrayList<>(Arrays.asList("3", item1.getId(), "N", "6")));
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item1.getId()).getName(), is("test name"));
    }

    @Test
    public void whenShowAllItems() {
        Input input = new StubInput(new ArrayList<>(Arrays.asList("1", "6")));
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("-------------------- Список всех заявок --------------------")
                        .append(System.lineSeparator())
                        .append("[")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("ID: ").append(item1.getId())
                        .append(System.lineSeparator())
                        .append("Имя: ").append(item1.getName())
                        .append(System.lineSeparator())
                        .append("Описание: ").append(item1.getDesc())
                        .append(System.lineSeparator())
                        .append("Создана: ").append(item1.getTime()).append(", ")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("ID: ").append(item2.getId())
                        .append(System.lineSeparator())
                        .append("Имя: ").append(item2.getName())
                        .append(System.lineSeparator())
                        .append("Описание: ").append(item2.getDesc())
                        .append(System.lineSeparator())
                        .append("Создана: ").append(item2.getTime()).append("]")
                        .append(System.lineSeparator())
                        .append(this.menu)
                        .toString()
                )
        );
    }

    @Test
    public void whenFindItemById() {
        Input input = new StubInput(new ArrayList<>(Arrays.asList("4", item1.getId(), "6")));
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("-------------------- Поиск по номеру заявки --------------------")
                        .append(System.lineSeparator())
                        .append("ID заявки: ").append(item1.getId())
                        .append(System.lineSeparator())
                        .append("Имя заявки: ").append(item1.getName())
                        .append(System.lineSeparator())
                        .append("Описание заявки: ").append(item1.getDesc())
                        .append(System.lineSeparator())
                        .append(this.menu)
                        .toString()
                )
        );
    }

    @Test
    public void whenFindItemsByName() {
        Input input = new StubInput(new ArrayList<>(Arrays.asList("5", "test name", "6")));
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("-------------------- Поиск по имени заявки --------------------")
                        .append(System.lineSeparator())
                        .append("[")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("ID: ").append(item1.getId())
                        .append(System.lineSeparator())
                        .append("Имя: ").append(item1.getName())
                        .append(System.lineSeparator())
                        .append("Описание: ").append(item1.getDesc())
                        .append(System.lineSeparator())
                        .append("Создана: ").append(item1.getTime()).append(", ")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("ID: ").append(item2.getId())
                        .append(System.lineSeparator())
                        .append("Имя: ").append(item2.getName())
                        .append(System.lineSeparator())
                        .append("Описание: ").append(item2.getDesc())
                        .append(System.lineSeparator())
                        .append("Создана: ").append(item2.getTime()).append("]")
                        .append(System.lineSeparator())
                        .append(this.menu)
                        .toString()
                )
        );
    }
}
