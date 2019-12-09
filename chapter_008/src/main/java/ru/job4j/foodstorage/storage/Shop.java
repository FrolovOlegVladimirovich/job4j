package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

import java.util.Date;
import java.util.List;

/**
 * Shop storage.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Shop implements IStorage {
    private final List<Food> foodList;

    /**
     * Default constructor.
     * @param foodList Food container.
     */
    public Shop(List<Food> foodList) {
        this.foodList = foodList;
    }

    /**
     * Adds food to the storage without discount if expiration date is used up in the range from 25% to 75%.
     * Adds food to the storage with discount if food has expired by more than 75% but has not yet expired.
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
        Date days75per = new Date(createDate + ((expiryDate - createDate) * 75 / 100));
        if (currentDate.compareTo(days25per) >= 0) {
            if (currentDate.compareTo(days75per) <= 0) {
                return true;
            } else if (currentDate.compareTo(food.getExpiryDate()) < 0) {
                food.applyDiscount();
                return true;
            }
        }
        return false;
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
}