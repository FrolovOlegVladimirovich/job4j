package ru.job4j.tracker;

import java.util.Objects;

/**
 * Класс реализует модель заявки.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.06.2019
 * @version 1.0
 */
public class Item {
    private String id;
    private String name;
    private String desc;
    private long time;

    /**
     * Стандартный конструктор класса без параметров.
     *
     */
    public Item() {
    }

    /**
     * Конструктор класса с параметрами.
     *
     * @param name - имя заявки.
     * @param desc - описание заявки.
     * @param time - время создания заявки.
     */
    public Item(String name, String desc, long time) {
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return time == item.time
                && Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(desc, item.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, time);
    }
}
