package ru.job4j.postgresql.trackersql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Реализация базы заявок через PostgreSQL.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 10.11.2019
 * @version 1.0
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private Connection connection;
    private String tableName;
    private final Logger log = LogManager.getLogger(TrackerSQL.class.getName());

    public TrackerSQL() {
    }

    public TrackerSQL(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    /**
     * Инициализирует соединение с базой данных SQL.
     * @return Состояние соединения с базой данных.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(in));
            tableName = config.getProperty("table_name");
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception exception) {
            log.error(exception);
        }
        return connection != null;
    }

    /**
     * Проверяет наличие необходимой структуры в базе tracker.
     * @return Результат проверки.
     */
    public boolean checkDataBaseStructure() {
        String resultTableReport = null;
        Set<String> checkColumnsReport = Set.of("id", "name", "description", "create_date");
        Set<String> resultColumnsReport = new HashSet<>();
        try (PreparedStatement checkTableStatement = connection.prepareStatement(String.format("%s %s %s",
                "SELECT table_name FROM information_schema.tables",
                "WHERE table_schema='public'",
                "ORDER BY table_name;"));
             PreparedStatement checkColumnsStatement = connection.prepareStatement(String.format("%s %s %s",
                     "SELECT column_name FROM information_schema.columns",
                     "WHERE table_schema='public'",
                     "ORDER BY table_name;"));
             ResultSet checkTableResultSet = checkTableStatement.executeQuery();
             ResultSet checkColumnsResultSet = checkColumnsStatement.executeQuery()) {
            while (checkTableResultSet.next()) {
                resultTableReport = checkTableResultSet.getString("table_name");
            }
            while (checkColumnsResultSet.next()) {
                resultColumnsReport.add(checkColumnsResultSet.getString("column_name"));
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return tableName.equals(resultTableReport) && resultColumnsReport.containsAll(checkColumnsReport);
    }

    /**
     * Создает структуру таблицы tableName.
     */
    public void createDataBaseStructure() {
        try (PreparedStatement statement = connection.prepareStatement(String.format("%s %s%s %s %s %s",
                "CREATE TABLE",
                tableName,
                "(id SERIAL PRIMARY KEY,",
                "name VARCHAR(30) NOT NULL,",
                "description VARCHAR(600) NOT NULL,",
                "create_date TIMESTAMP NOT NULL DEFAULT NOW());"))) {
            statement.execute();
        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    /**
     * Добавление заявки в базу с присвоением уникального ID.
     *
     * @param item - объект заявка.
     * @return возвращает объект заявки с присвоенным номером ID.
     */
    @Override
    public Item add(Item item) {
        String name = item.getName();
        String description = item.getDesc();
        Item result = new Item();
        try (PreparedStatement insertStatement = connection.prepareStatement(String.format("%s %s %s",
                "INSERT INTO",
                tableName,
                "(name, description) VALUES (?, ?);"));
             PreparedStatement selectStatement = connection.prepareStatement(String.format("%s %s %s",
                     "SELECT * FROM",
                     tableName,
                     "WHERE name= ? AND description = ?;"))) {
            insertStatement.setString(1, name);
            insertStatement.setString(2, description);
            insertStatement.execute();
            selectStatement.setString(1, name);
            selectStatement.setString(2, description);
            ResultSet selectResult = selectStatement.executeQuery();
            while (selectResult.next()) {
                result.setId((String.valueOf(selectResult.getInt("id"))));
                result.setName(selectResult.getString("name"));
                result.setDesc(selectResult.getString("description"));
                result.setTime(selectResult.getTimestamp("create_date").getTime());
            }
            selectResult.close();
        } catch (SQLException exception) {
            log.error(exception);
        }
        return result;
    }

    /**
     * Редактирует выбранную по номеру ID заявку.
     *
     * @param id   - уникальный номер редактируемой заявки в базе.
     * @param item - объект заявка, для замены редактируемой.
     * @return возвращает true, если заявка успешно отредактирована.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        String name = item.getName();
        String description = item.getDesc();
        try (PreparedStatement updateStatement = connection.prepareStatement(String.format("%s %s %s",
                "UPDATE",
                tableName,
                "SET name = ?, description = ? WHERE id = ?;"));
             PreparedStatement selectStatement = connection.prepareStatement(String.format("%s %s %s",
                     "SELECT * FROM",
                     tableName,
                     "WHERE name = ? AND description = ?;"))) {
            updateStatement.setString(1, name);
            updateStatement.setString(2, description);
            updateStatement.setInt(3, Integer.parseInt(id));
            updateStatement.execute();
            selectStatement.setString(1, name);
            selectStatement.setString(2, description);
            ResultSet selectResult = selectStatement.executeQuery();
            while (selectResult.next()) {
                if (name.equals(selectResult.getString("name"))
                        && description.equals(selectResult.getString("description"))) {
                    result = true;
                }
            }
            selectResult.close();
        } catch (SQLException exception) {
            log.error(exception);
        }
        return result;
    }

    /**
     * Удаление заявки из базы.
     *
     * @param id - уникальный номер удаляемой заявки в базе.
     * @return возвращает true, если заявка успешно удалена.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement deleteStatement = connection.prepareStatement(String.format("%s %s %s",
                "DELETE FROM",
                tableName,
                "WHERE id = ?;"));
             PreparedStatement selectStatement = connection.prepareStatement(String.format("%s %s %s",
                     "SELECT * FROM",
                     tableName,
                     "WHERE id = ?;"))) {
            deleteStatement.setInt(1, Integer.parseInt(id));
            deleteStatement.execute();
            selectStatement.setInt(1, Integer.parseInt(id));
            ResultSet selectResult = selectStatement.executeQuery();
            if (!selectResult.isBeforeFirst()) {
                result = true;
            }
            selectResult.close();
        } catch (SQLException exception) {
            log.error(exception);
        }
        return result;
    }

    /**
     * Получение списка всех заявок в базе.
     *
     * @return возвращает все заявки в базе в виде List объектов типа Item.
     */
    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement selectStatement = connection.prepareStatement(String.format("%s %s%s",
                "SELECT * FROM",
                tableName,
                ";"))) {
            ResultSet selectResult = selectStatement.executeQuery();
            while (selectResult.next()) {
                Item item = new Item(selectResult.getString("name"),
                        selectResult.getString("description"),
                        selectResult.getTimestamp("create_date").getTime());
                item.setId(String.valueOf(selectResult.getInt("id")));
                result.add(item);
            }
            selectResult.close();
        } catch (SQLException exception) {
            log.error(exception);
        }
        return result;
    }

    /**
     * Поиск заявок по имени.
     *
     * @param key - имя заявки, которую ищем.
     * @return возвращает все найденные в базе заявки в виде List объектов типа Item.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement selectStatement = connection.prepareStatement(String.format("%s %s %s",
                "SELECT * FROM",
                tableName,
                "WHERE name = ?;"))) {
            selectStatement.setString(1, key);
            ResultSet selectResult = selectStatement.executeQuery();
            while (selectResult.next()) {
                Item item = new Item(selectResult.getString("name"),
                        selectResult.getString("description"),
                        selectResult.getTimestamp("create_date").getTime());
                item.setId(String.valueOf(selectResult.getInt("id")));
                result.add(item);
            }
            selectResult.close();
        } catch (SQLException exception) {
            log.error(exception);
        }
        return result;
    }

    /**
     * Поиск заявок по ID.
     *
     * @param id - уникальный номер заявки, которую ищем.
     * @return возвращает найденную заявку в виде объекта типа Item.
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(String.format("%s %s %s",
                "SELECT * FROM",
                tableName,
                "WHERE id = ?;"))) {
            selectStatement.setInt(1, Integer.parseInt(id));
            ResultSet selectResult = selectStatement.executeQuery();
            while (selectResult.next()) {
                result = new Item(selectResult.getString("name"),
                        selectResult.getString("description"),
                        selectResult.getTimestamp("create_date").getTime());
                result.setId(String.valueOf(selectResult.getInt("id")));
            }
            selectResult.close();
        } catch (SQLException exception) {
            log.error(exception);
        }
        return result;
    }

    /**
     * Возвращает количество заявок в базе.
     *
     * @return Количество заявок в базе.
     */
    @Override
    public int getPosition() {
        int result = 0;
        try (PreparedStatement selectStatement = connection.prepareStatement(String.format("%s %s%s",
                "SELECT COUNT(id) FROM",
                tableName,
                ";"))
        ) {
            ResultSet selectResult = selectStatement.executeQuery();
            while (selectResult.next()) {
                result = selectResult.getInt("count");
            }
            selectResult.close();
        } catch (SQLException exception) {
            log.error(exception);
        }
        return result;
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     * {@code try}-with-resources statement.
     *
     * @throws Exception if this resource cannot be closed
     */
    @Override
    public void close() throws Exception {
        connection.close();
    }
}