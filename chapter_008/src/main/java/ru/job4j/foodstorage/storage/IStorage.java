package ru.job4j.foodstorage.storage;


import ru.job4j.foodstorage.food.Food;

import java.util.Date;

/**
 * Methods for the implementation of storage products.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface IStorage {

    /**
     * Add food to the storage.
     * @param food Food.
     * @param currentDate Current date to check food expiration date.
     * @return true, if food was successfully added to the storage.
     */
    boolean add(Food food, Date currentDate);

    /**
     * Checks if this food is in the storage.
     * @param food Food.
     * @return true, if this food is in the storage.
     */
    boolean containsFood(Food food);

    /**
     * Removes food from the storage.
     * @param food Food.
     * @return true, if food was successfully removed from the storage.
     */
    boolean deleteFood(Food food);

    /**
     * Gets food from the storage by name.
     * @param name Food name.
     * @return Returns the food if it's found, otherwise returns null.
     */
    Food getFood(String name);

    /**
     * Checks food expiration date.
     * @param food Food.
     * @param currentDate Current date to check food expiration date.
     * @return true, if expiration date of the food fits the conditions for adding to this storage.
     */
    boolean checkExpiryDate(Food food, Date currentDate);
}