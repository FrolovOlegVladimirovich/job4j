package ru.job4j.statistics;

/**
 * Статистика по изменению коллекции.
 *
 * added - количество добавленных элементов.
 * changed - количество измененных элементов.
 * deleted - количество удаленных элементов.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 19.09.2019
 * @version 1.0
 */
public class Info {
    private int added;
    private int changed;
    private int deleted;

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added += added;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed += changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted += deleted;
    }

    @Override
    public String toString() {
        return "Info{"
                + "added="
                + added
                + ", changed="
                + changed
                + ", deleted=" + deleted
                + '}';
    }
}