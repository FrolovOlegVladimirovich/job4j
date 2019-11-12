package ru.job4j.xmlmagnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Взаимодействие с базой данных SQLite.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 12.11.2019
 * @version 1.0
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connection;
    private String tableName;

    /**
     * Конструктор принимает конфигурацию настроек для базы данных.
     * @param config Конфигурация.
     */
    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * Создает соединение с базой данных.
     */
    public void initConnection() {
        String url = config.get("url") + System.getProperty("java.io.tmpdir") + "store.db";
        String username = config.get("username");
        String password = config.get("password");
        tableName = config.get("tablename");
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Генерирует в базе данных n записей.
     * @param size Количество записей для генерации.
     */
    public void generate(int size) {
        try (PreparedStatement createTableIfNotExists = connection.prepareStatement(String.format("%s %s %s",
                "CREATE TABLE IF NOT EXISTS",
                tableName,
                "(field INTEGER);"))) {
            connection.setAutoCommit(false);
            createTableIfNotExists.execute();
            PreparedStatement deleteAllEntries = connection.prepareStatement(String.format("%s %s%s",
                    "DELETE FROM",
                    tableName,
                    ";"));
            deleteAllEntries.execute();
            PreparedStatement insert = connection.prepareStatement(String.format("%s %s %s",
                    "INSERT INTO",
                    tableName,
                    "VALUES(?);"
            ));
            for (int i = 1; i < size + 1; i++) {
                insert.setInt(1, i);
                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
            deleteAllEntries.close();
            insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Загружает все значения колонки "field" из базы данных в List<Entry>.
     * @return Список Entry со значениями, полученными из колонки "field" базы данных.
     */
    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        try (PreparedStatement selectAllEntries = connection.prepareStatement(String.format("%s %s%s",
                "SELECT * FROM",
                tableName,
                ";"))) {
            ResultSet resultSet = selectAllEntries.executeQuery();
            while (resultSet.next()) {
                result.add(new Entry(resultSet.getInt("field")));
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        if (connection != null) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}