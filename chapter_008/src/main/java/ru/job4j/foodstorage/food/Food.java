package ru.job4j.foodstorage.food;

import java.util.Date;
import java.util.Objects;

/**
 * Abstract food class.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public abstract class Food {
    private final String name;
    private double price;
    private double discountPercent;
    private final Date createDate;
    private final Date expiryDate;

    /**
     * Default constructor.
     * @param name Name.
     * @param price Price.
     * @param createDate Production date.
     * @param expiryDate Expiration date.
     */
    public Food(String name, double price, Date createDate, Date expiryDate) {
        this.name = name;
        this.price = price;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
    }

    /**
     * Sets the percentage discount.
     * @param discountPercent Discount percentage.
     */
    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    /**
     * Apply a discount to the current price.
     */
    public void applyDiscount() {
        this.price = this.price - (this.price * this.discountPercent / 100);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='"
                + name
                + '\''
                + ", price=" + price
                + ", discountPercent="
                + discountPercent
                + ", createDate="
                + createDate
                + ", expiryDate="
                + expiryDate
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}