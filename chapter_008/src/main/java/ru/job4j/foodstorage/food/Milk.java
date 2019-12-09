package ru.job4j.foodstorage.food;

import java.util.Date;

public class Milk extends Food {
    public Milk(String name, double price, Date createDate, Date expiryDate) {
        super(name, price, createDate, expiryDate);
    }
}