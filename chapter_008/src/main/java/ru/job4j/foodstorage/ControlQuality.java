package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.storage.IStorage;

import java.util.Date;
import java.util.List;

/**
 * Product quality check.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class ControlQuality {
    private List<IStorage> storageList;

    public ControlQuality() {
    }

    /**
     * Constructor.
     * @param storageList List of different storage.
     */
    public ControlQuality(List<IStorage> storageList) {
        this.storageList = storageList;
    }

    /**
     * Adds storage.
     * @param storage Storage.
     * @return true, if storage was successfully added.
     */
    public boolean addStorage(IStorage storage) {
        return this.storageList.add(storage);
    }

    /**
     * Adds storage list.
     * @param storageList Storage list.
     * @return true, if storage list was successfully added.
     */
    public boolean addStorage(List<IStorage> storageList) {
        return this.storageList.addAll(storageList);
    }

    /**
     * Checking the food for the expiration date,
     * as a result of which the food will be sent to the appropriate warehouse.
     * @param product Food.
     * @param currentDate Current date to check food expiration date.
     */
    public void check(Food product, Date currentDate) {
        for (IStorage storage: storageList) {
            if (storage.add(product, currentDate)) {
                break;
            }
        }
    }

    /**
     * Checking the list of food for the expiration date,
     * as a result of which every food will be sent to the appropriate warehouse.
     * @param products List of food.
     * @param currentDate Current date to check food expiration date.
     */
    public void check(List<Food> products, Date currentDate) {
        for (IStorage storage: storageList) {
            for (Food product: products) {
                if (storage.add(product, currentDate)) {
                    break;
                }
            }
        }
    }
}