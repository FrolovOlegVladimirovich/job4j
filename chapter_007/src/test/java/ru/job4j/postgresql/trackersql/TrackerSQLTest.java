package ru.job4j.postgresql.trackersql;

import org.junit.*;
import ru.job4j.tracker.Item;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тесты базы заявок SQL.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 10.11.2019
 * @version 1.0
 */
@Ignore
public class TrackerSQLTest {
    private static TrackerSQL sql;
    private static final String TABLE_NAME = "items";
    private static boolean initResult;
    private static boolean checkDataBaseStructureFalse;
    private static boolean checkDataBaseStructureTrue;
    private static Connection testConnection;

    /**
     * Устанавливает соединение с базой данных и создает таблицу с необходимой структурой.
     */
    @BeforeClass
    public static void setUp() throws Exception {
        sql = new TrackerSQL();
        initResult = sql.init();
        checkDataBaseStructureFalse = sql.checkDataBaseStructure();
        if (!checkDataBaseStructureFalse) {
            sql.createDataBaseStructure();
            checkDataBaseStructureTrue = sql.checkDataBaseStructure();
        }
        Class.forName("org.postgresql.Driver");
        testConnection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/tracker",
                "postgres",
                "password");
    }

    /**
     * Удаляет таблицу в базе данных и закрывает соединения.
     * @throws Exception - возможны исключения.
     */
    @AfterClass
    public static void closeConnectionAndClearDataBase() throws Exception {
        PreparedStatement statement = testConnection.prepareStatement(String.format("%s %s%s",
                "DROP TABLE",
                TABLE_NAME,
                ";"));
        statement.execute();
        statement.close();
        sql.close();
        testConnection.close();
    }

    /**
     * После каждого теста удаляет все строки в таблице.
     * @throws SQLException - возможное исключение.
     */
    @After
    public void deleteAllEntries() throws SQLException {
        PreparedStatement statement = testConnection.prepareStatement(String.format("%s %s%s",
                "DELETE FROM",
                TABLE_NAME,
                ";"));
        statement.execute();
        statement.close();
    }

    @Test
    public void whenConnectionEstablishedResultIsTrue() {
        assertThat(initResult, is(true));
    }

    @Test
    public void whenDataBaseStructureExistsResultIsTrue() {
        assertThat(checkDataBaseStructureFalse, is(false));
        assertThat(checkDataBaseStructureTrue, is(true));
    }

    @Test
    public void whenAddItemToTheDatabase() {
        String nameItem = "item test";
        String descriptionItem = "description test";
        Item expectItem = new Item(nameItem, descriptionItem);
        Item resultItem = sql.add(expectItem);

        Set<String> expect = Set.of(nameItem, descriptionItem);
        Set<String> result = Set.of(resultItem.getName(), resultItem.getDesc());

        assertThat(result, is(expect));
    }

    @Test
    public void whenEditItemNameAndDescriptionById() {
        Item item = sql.add(new Item("item test", "description test"));
        String id = item.getId();
        String nameItem = "item editing from Java";
        String descriptionItem = "I edit this item from Java!";

        Item expectItem = new Item(nameItem, descriptionItem);
        boolean result = sql.replace(id, expectItem);

        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteItemById() {
        sql.add(new Item("item test", "description test"));
        String id = "1";
        boolean result = sql.delete(id);

        assertThat(result, is(true));
    }

    @Test
    public void whenFindAllItemsInDatabase() {
        Item item1 = new Item("item test - 1", "description test - 1");
        Item item2 = new Item("item test - 2", "description test - 2");
        Item item3 = new Item("item test - 3", "description test - 3");
        Item item4 = new Item("item test - 4", "description test - 4");
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        sql.add(item4);

        Set<String> expect = Set.of(
                item1.getName(), item2.getName(), item3.getName(), item4.getName(),
                item1.getDesc(), item2.getDesc(), item3.getDesc(), item4.getDesc()
        );
        Set<String> result = new HashSet<>();
        List<Item> items = sql.findAll();
        items.forEach(item -> {
            result.add(item.getName());
            result.add(item.getDesc());
        });

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindItemsByName() {
        Item item1 = new Item("item test - 1", "description test - 1");
        Item item2 = new Item("item test - 2", "description test - 2");
        Item item3 = new Item("item test - 3", "description test - 3");
        Item item4 = new Item("item test - 4", "description test - 4");
        Item item5 = new Item("item test - 3", "duplicate description test - 3");
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        sql.add(item4);
        sql.add(item5);

        Set<String> expect = Set.of(item3.getDesc(), item5.getDesc());
        Set<String> result = new HashSet<>();
        sql.findByName("item test - 3").forEach(item -> result.add(item.getDesc()));

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindItemById() {
        Item item1 = new Item("item 1", "description 1");
        sql.add(item1);
        Item item2 = new Item("item 2", "description 2");
        item2.setId("2");
        sql.add(item2);
        Item item3 = new Item("item 3", "description 3");
        item3.setId("3");
        String item3Id = sql.add(item3).getId();
        Item item4 = new Item("item 4", "description 4");
        item4.setId("4");
        sql.add(item4);

        String result = sql.findById(item3Id).getName();

        assertThat(result, is(item3.getName()));
    }

    @Test
    public void whenGetTotalNumberOfItemsInDatabase() {
        Item item1 = new Item("item 1", "description 1");
        Item item2 = new Item("item 2", "description 2");
        Item item3 = new Item("item 3", "description 3");
        Item item4 = new Item("item 4", "description 4");
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        sql.add(item4);

        assertThat(sql.getPosition(), is(4));
    }
}