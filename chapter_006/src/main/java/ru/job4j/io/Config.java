package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Читает файл конфигурации (app.properties),
 * добавляя параметры ключ и значение в Map.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.10.2019
 * @version 1.0
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    /**
     * Конструктор присваивает путь к файлу с настройками.
     * @param path Путь к файлу конфигураций.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Загружает настройки конфигурации в values в виде пары ключ-значение.
     *
     * Используется буфферизация ввода.
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(line -> line.contains("="))
                    .map(line -> line.split("="))
                    .forEach(
                            array -> {
                                if (array.length == 2) {
                                    values.put(array[0], array[1]);
                                }
                            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает значение конфигурации по ключу.
     * @param key Ключ.
     * @return Значение конфигурации.
     */
    public String value(String key) {
        return values.get(key);
    }

    /**
     * @return Возвращает Map ключей и значений кофигурации.
     */
    public Map<String, String> getValues() {
        return values;
    }

    /**
     * @return Возвращает содержание из файла кофигурации в виде текста.
     */
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}