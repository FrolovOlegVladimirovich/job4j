package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Класс-обертка над базой заявок в виде массива.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.06.2019
 * @version 1.0
 */
public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;

    /**
     * Добавление заявки в базу с присвоением уникального ID.
     *
     * @param item - объект заявка.
     * @return возвращает объект заявки с присвоенным номером ID.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
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
        for (int i = 0; i != this.position; i++) {
            if (id.equals(this.items[i].getId())) {
                System.arraycopy(this.items, i + 1, this.items, i, (this.position - i));
                this.items[this.position--] = null;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Получение списка всех заявок в базе.
     *
     * @return возвращает все заявки в базе в виде массива объектов типа Item.
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * Поиск заявок по имени.
     * @param key - имя заявки, которую ищем.
     * @return возвращает все найденные в базе заявки в виде массива объектов типа Item.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int length = 0;
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                result[length++] = this.items[i];
            }
        }
        return Arrays.copyOf(result, length);
    }

    /**
     * Поиск заявок по ID.
     * @param id - уникальный номер заявки, которую ищем.
     * @return возвращает найденную заявку в виде объекта типа Item.
     */
    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = this.items[i];
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