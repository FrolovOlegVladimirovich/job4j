package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    /**
     * Тест add()
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        long created = System.currentTimeMillis();
        Item item = new Item("test 1", "testDescription", created);
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    /**
     * Тест replace()
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Тест delete()
     */
    @Test
    public void whenDeleteItemThreeThenReturnArrayWithoutDeletedItem() {
        Tracker tracker = new Tracker();
        List<Item> ex = Arrays.asList(
                tracker.add(new Item("test1", "testDescription1", 1L)),
                tracker.add(new Item("test2", "testDescription2", 12L)),
                tracker.add(new Item("test3", "testDescription3", 1234L)));
        Item item = tracker.add(new Item("test4", "testDescription4", 123L));
        tracker.delete(item.getId());
        List<Item> list = tracker.findAll();
        assertThat(list, is(ex));
    }

    /**
     * Тест findAll()
     */
    @Test
    public void whenTwoItemsAddedInTrackerEqualsAnotherArray() {
        Tracker tracker = new Tracker();
        Item one = new Item("test1", "testDescription1", 1L);
        Item two = new Item("test2", "testDescription2", 12L);
        ArrayList<Item> result = new ArrayList<>(Arrays.asList(one, two));
        tracker.add(one);
        tracker.add(two);
        assertThat(tracker.findAll(), is(result));
    }

    /**
     * Тест findByName()
     */
    @Test
    public void findTwoItemsWithTheSameName() {
        Tracker tracker = new Tracker();
        Item one = new Item("test1", "testDescription1", 1L);
        Item two = new Item("test4", "testDescription2", 12L);
        Item three = new Item("test3", "testDescription3", 123L);
        Item four = new Item("test4", "testDescription4", 1234L);
        ArrayList<Item> result = new ArrayList<>(Arrays.asList(two, four));
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        tracker.add(four);
        assertThat(tracker.findByName("test4"), is(result));
    }

    /**
     * Тест findById()
     */
    @Test
    public void findItemById() {
        Tracker tracker = new Tracker();
        Item one = new Item("test1", "testDescription1", 1L);
        Item two = new Item("test4", "testDescription2", 12L);
        Item three = new Item("test3", "testDescription3", 123L);
        Item four = new Item("test4", "testDescription4", 1234L);
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        tracker.add(four);
        Item result = tracker.findById(four.getId());
        assertThat(result.getId(), is(four.getId()));
    }
}

