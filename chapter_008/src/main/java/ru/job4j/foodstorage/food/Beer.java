package ru.job4j.foodstorage.food;

import java.util.Date;

public class Beer extends Food {
    public Beer(String name, double price, Date createDate, Date expiryDate) {
        super(name, price, createDate, expiryDate);
    }
}