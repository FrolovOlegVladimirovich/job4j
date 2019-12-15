package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Warehouse storage.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Warehouse implements IStorage {
    private final List<Food> foodList;

    /**
     * Default constructor.
     * @param foodList Food container.
     */
    public Warehouse(List<Food> foodList) {
        this.foodList = foodList;
    }

    /**
     * Adds food to the storage if the expiration date is less than 25%.
     * @param food Food.
     * @param currentDate Current date to check food expiration date.
     * @return true, if food was successfully added to the storage.
     */
    @Override
    public boolean add(Food food, Date currentDate) {
        if (checkExpiryDate(food, currentDate)) {
            this.foodList.add(food);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExpiryDate(Food food, Date currentDate) {
        long createDate = food.getCreateDate().getTime();
        long expiryDate = food.getExpiryDate().getTime();
        Date days25per = new Date(createDate + ((expiryDate - createDate) * 25 / 100));
        return currentDate.compareTo(days25per) < 0;
    }

    @Override
    public boolean containsFood(Food food) {
        return this.foodList.contains(food);
    }

    @Override
    public boolean deleteFood(Food food) {
        return this.foodList.remove(food);
    }

    @Override
    public Food getFood(String name) {
        Food result = null;
        for (Food food: foodList) {
            if (name.equals(food.getName())) {
                result = food;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Food> getAllFood() {
        var result = new ArrayList<>(foodList);
        foodList.clear();
        return result;
    }
}