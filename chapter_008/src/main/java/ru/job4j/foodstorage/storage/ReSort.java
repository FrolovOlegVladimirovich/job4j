package ru.job4j.foodstorage.storage;

import java.util.Date;

/**
 * Re-sort food in storage according to a certain condition.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface ReSort {
    /**
     * Re-sort food in storage depending on expiration date.
     * @param currentDate Current date.
     */
    void reSort(Date currentDate);
}