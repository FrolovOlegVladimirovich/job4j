package ru.job4j.tracker;

import java.util.List;

/**
 * Интерфейс с описанием методов для базы заявок.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 10.11.2019
 * @version 1.0
 */
public interface ITracker {

    /**
     * Добавление заявки в базу с присвоением уникального ID.
     *
     * @param item - объект заявка.
     * @return возвращает объект заявки с присвоенным номером ID.
     */
    Item add(Item item);

    /**
     * Редактирует выбранную по номеру ID заявку.
     *
     * @param id - уникальный номер редактируемой заявки в базе.
     * @param item - объект заявка, для замены редактируемой.
     * @return возвращает true, если заявка успешно отредактирована.
     */
    boolean replace(String id, Item item);

    /**
     * Удаление заявки из базы.
     *
     * @param id - уникальный номер удаляемой заявки в базе.
     * @return возвращает true, если заявка успешно удалена.
     */
    boolean delete(String id);

    /**
     * Получение списка всех заявок в базе.
     *
     * @return возвращает все заявки в базе в виде List объектов типа Item.
     */
    List<Item> findAll();

    /**
     * Поиск заявок по имени.
     * @param key - имя заявки, которую ищем.
     * @return возвращает все найденные в базе заявки в виде List объектов типа Item.
     */
    List<Item> findByName(String key);

    /**
     * Поиск заявок по ID.
     * @param id - уникальный номер заявки, которую ищем.
     * @return возвращает найденную заявку в виде объекта типа Item.
     */
    Item findById(String id);

    /**
     * Возвращает количество заявок в базе.
     * @return Количество заявок в базе.
     */
    int getPosition();
}