package ru.job4j.tracker;

/**
 * Абстрактный класс, реализующий действия в трекере.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 19.06.2019
 * @version 1.0
 */
public abstract class BaseAction implements UserAction {
    private final int key;
    private final String name;

    protected BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
