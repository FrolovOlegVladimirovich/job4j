package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "test desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add((new Item("test name", "desc")));
        Input input = new StubInput(new String[]{"2", item.getId(), "new test name", "new desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("new test name"));
    }

    @Test
    public void whenDeleteThenTrackerHasNoItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add((new Item("test name", "desc")));
        Input input = new StubInput(new String[]{"3", item.getId(), "", "6"});
        new StartUI(input, tracker).init();
        Item result = null;
        assertThat(tracker.findById(item.getId()), is(result));
    }

    @Test
    public void whenCancelDeleteThenTrackerNoDelete() {
        Tracker tracker = new Tracker();
        Item item = tracker.add((new Item("test name", "desc")));
        Input input = new StubInput(new String[]{"3", item.getId(), "N", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }
}
