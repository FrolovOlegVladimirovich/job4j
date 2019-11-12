package ru.job4j.xmlmagnit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Конфигурация настроек для подключения к базе данных.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 12.11.2019
 * @version 1.0
 */
public class Config {
    private final Properties values = new Properties();

    /**
     * Загружает настройки базы данных (url, username, password, tablename)
     * из файла "xmlmagnit.properties" в Properties values.
     */
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("xmlmagnit.properties")) {
            values.load(Objects.requireNonNull(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}