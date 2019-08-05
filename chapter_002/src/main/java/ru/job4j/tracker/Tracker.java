package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-обертка над базой заявок в виде ArrayList.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.06.2019
 * @version 1.0
 */
public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private int position = 0;

    /**
     * Добавление заявки в базу с присвоением уникального ID.
     *
     * @param item - объект заявка.
     * @return возвращает объект заявки с присвоенным номером ID.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(this.position++, item);
        return item;
    }

    /**
     * Генерация уникального ID для заявки.
     *
     * @return возвращает уникальный ID для заявки.
     */
    public String generateId() {
        Long id =  System.currentTimeMillis() * (long) (1 + Math.random() * 101);
        return id.toString();
    }

    /**
     * Редактирует выбранную по номеру ID заявку.
     *
     * @param id - уникальный номер редактируемой заявки в базе.
     * @param item - объект заявка, для замены редактируемой.
     * @return возвращает true, если заявка успешно отредактирована.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i != this.position; i++) {
            if (this.items.get(i).getId().equals(id)) {
                this.items.set(i, item);
                result = true;
            }
        }
        return result;
    }

    /**
     * Удаление заявки из базы.
     *
     * @param id - уникальный номер удаляемой заявки в базе.
     * @return возвращает true, если заявка успешно удалена.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i != this.items.size(); i++) {
            if (id.equals(this.items.get(i).getId())) {
                this.items.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Получение списка всех заявок в базе.
     *
     * @return возвращает все заявки в базе в виде ArrayList объектов типа Item.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Поиск заявок по имени.
     * @param key - имя заявки, которую ищем.
     * @return возвращает все найденные в базе заявки в виде ArrayList объектов типа Item.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Поиск заявок по ID.
     * @param id - уникальный номер заявки, которую ищем.
     * @return возвращает найденную заявку в виде объекта типа Item.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает количество заявок в базе.
     */
    public int getPosition() {
        return position;
    }
}