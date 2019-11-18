package ru.job4j.postgresql.trackersql;

import org.junit.*;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тесты базы заявок SQL.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 10.11.2019
 * @version 1.0
 */
public class TrackerSQLTest {
    private final Properties config = new Properties();
    private String tableName;

    public Connection init() {
        Connection result = null;
        try (InputStream propertiesStream = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(Objects.requireNonNull(propertiesStream));
            Class.forName(config.getProperty("driver-class-name"));
            tableName = config.getProperty("table_name");
            result = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public void whenAddItemToTheDatabase() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()), tableName)) {
            trackerSQL.add(new Item("item", "description"));

            assertThat(trackerSQL.findByName("item").size(), is(1));
        }
    }

    @Test
    public void whenEditItemNameAndDescriptionById() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()), tableName)) {
            String id = trackerSQL.add(new Item("item", "description")).getId();

            String nameItem = "item editing from Java";
            String descriptionItem = "I edit this item from Java!";
            boolean result = trackerSQL.replace(id, new Item(nameItem, descriptionItem));

            assertThat(trackerSQL.findByName("item editing from Java").size(), is(1));
            assertTrue(result);
        }
    }

    @Test
    public void whenDeleteItemById() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()), tableName)) {
            String id = trackerSQL.add(new Item("item", "description")).getId();

            boolean result = trackerSQL.delete(id);

            assertThat(trackerSQL.findByName("item").size(), is(0));
            assertTrue(result);
        }

    }

    @Test
    public void whenFindAllItemsInDatabase() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()), tableName)) {
            Item item1 = new Item("item test - 1", "description test - 1");
            Item item2 = new Item("item test - 2", "description test - 2");
            Set<String> expect = Set.of(
                    item1.getName(), item2.getName(),
                    item1.getDesc(), item2.getDesc()
            );

            trackerSQL.add(item1);
            trackerSQL.add(item2);
            Set<String> result = new HashSet<>();
            List<Item> items = trackerSQL.findAll();
            items.forEach(item -> {
                result.add(item.getName());
                result.add(item.getDesc());
            });

            assertThat(result, is(expect));
        }
    }

    @Test
    public void whenFindItemsByName() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()), tableName)) {
            Item item1 = new Item("item 1", "description 1");
            Item item2 = new Item("item 2", "description 2");
            Item item3 = new Item("item 3", "description 3");
            Item item4 = new Item("item 4", "description 4");
            Item item5 = new Item("item 3", "duplicate 3");
            trackerSQL.add(item1);
            trackerSQL.add(item2);
            trackerSQL.add(item3);
            trackerSQL.add(item4);
            trackerSQL.add(item5);

            Set<String> expect = Set.of(item3.getDesc(), item5.getDesc());
            Set<String> result = new HashSet<>();
            trackerSQL.findByName("item 3").forEach(item -> result.add(item.getDesc()));

            assertThat(result, is(expect));
        }
    }

    @Test
    public void whenFindItemById() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()), tableName)) {
            trackerSQL.add(new Item("item 1", "description 1"));
            String item2Id = trackerSQL.add(new Item("item 2", "description 2")).getId();

            String result = trackerSQL.findById(item2Id).getName();

            assertThat(result, is("item 2"));
        }
    }

    @Test
    public void whenGetTotalNumberOfItemsInDatabase() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()), tableName)) {
            Item item1 = new Item("item 1", "description 1");
            Item item2 = new Item("item 2", "description 2");
            Item item3 = new Item("item 3", "description 3");
            Item item4 = new Item("item 4", "description 4");

            trackerSQL.add(item1);
            trackerSQL.add(item2);
            trackerSQL.add(item3);
            trackerSQL.add(item4);

            assertThat(trackerSQL.getPosition(), is(4));
            assertThat(trackerSQL.findAll().size(), is(4));
        }
    }
}