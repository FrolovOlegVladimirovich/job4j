package ru.job4j.foodstorage;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.foodstorage.food.Beer;
import ru.job4j.foodstorage.food.Chocolate;
import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.food.Milk;
import ru.job4j.foodstorage.storage.IStorage;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.Warehouse;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * ControlQuality tests.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class ControlQualityTest {
    Food chocolate;
    IStorage warehouse;
    IStorage shop;
    IStorage trash;
    List<IStorage> storageList;

    @Before
    public void setUp() {
        warehouse = new Warehouse(new ArrayList<>());
        shop = new Shop(new ArrayList<>());
        trash = new Trash(new ArrayList<>());
        storageList = List.of(warehouse, shop, trash);
        chocolate = new Chocolate(
                "Alpen Gold",
                100,
                new GregorianCalendar(2019, Calendar.DECEMBER, 1).getTime(),
                new GregorianCalendar(2020, Calendar.MARCH, 15).getTime()
        );
        chocolate.setDiscountPercent(25);
    }

    @Test
    public void whenFoodShouldBeInWarehouse() {
        Date today = new GregorianCalendar(2019, Calendar.DECEMBER, 10).getTime();

        ControlQuality controlQuality = new ControlQuality(storageList);
        controlQuality.check(chocolate, today);
        double expect = warehouse.getFood("Alpen Gold").getPrice();

        assertTrue(warehouse.containsFood(chocolate));
        assertThat(expect, is(100D));
    }

    @Test
    public void whenFoodShouldBeInTrash() {
        Date today = new GregorianCalendar(2020, Calendar.MARCH, 15).getTime();

        ControlQuality controlQuality = new ControlQuality(storageList);
        controlQuality.check(chocolate, today);
        double expect = trash.getFood("Alpen Gold").getPrice();

        assertTrue(trash.containsFood(chocolate));
        assertThat(expect, is(100D));
    }

    @Test
    public void whenFoodShouldBeInShopWithoutDiscount() {
        Date today = new GregorianCalendar(2020, Calendar.FEBRUARY, 10).getTime();

        ControlQuality controlQuality = new ControlQuality(storageList);
        controlQuality.check(chocolate, today);
        double expect = shop.getFood("Alpen Gold").getPrice();

        assertTrue(shop.containsFood(chocolate));
        assertThat(expect, is(100D));
    }

    @Test
    public void whenFoodShouldBeInShopWithDiscount() {
        Date today = new GregorianCalendar(2020, Calendar.MARCH, 13).getTime();

        ControlQuality controlQuality = new ControlQuality(storageList);
        controlQuality.check(chocolate, today);
        double expect = shop.getFood("Alpen Gold").getPrice();

        assertTrue(shop.containsFood(chocolate));
        assertThat(expect, is(75D));
    }

    @Test
    public void whenAddListOfFood() {
        Date today = new GregorianCalendar(2019, Calendar.DECEMBER, 10).getTime();
        Food milk = new Milk(
                "House in the village",
                100,
                new GregorianCalendar(2019, Calendar.NOVEMBER, 1).getTime(),
                new GregorianCalendar(2019, Calendar.DECEMBER, 20).getTime()
        );
        milk.setDiscountPercent(50);
        Food beer = new Beer(
                "Miller",
                100,
                new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.OCTOBER, 15).getTime()
        );
        beer.setDiscountPercent(10);
        List<Food> foodList = List.of(chocolate, beer, milk);

        ControlQuality controlQuality = new ControlQuality(storageList);
        controlQuality.check(foodList, today);
        double expectChocoPrice = warehouse.getFood("Alpen Gold").getPrice();
        double expectBeerPrice = trash.getFood("Miller").getPrice();
        double expectMilkPrice = shop.getFood("House in the village").getPrice();

        assertTrue(trash.containsFood(beer));
        assertTrue(shop.containsFood(milk));
        assertTrue(warehouse.containsFood(chocolate));
        assertThat(expectMilkPrice, is(50D));
        assertThat(expectChocoPrice, is(100D));
        assertThat(expectBeerPrice, is(100D));
    }


    @Test
    public void reSortTest() {
        Date today = new GregorianCalendar(2019, Calendar.DECEMBER, 15).getTime();
        Date reSortDay = new GregorianCalendar(2020, Calendar.MARCH, 10).getTime();
        ControlQuality controlQuality = new ControlQuality(storageList);
        controlQuality.check(chocolate, today);

        controlQuality.reSort(reSortDay);
        double expectChocoPrice = shop.getFood("Alpen Gold").getPrice();


        assertTrue(shop.containsFood(chocolate));
        assertThat(expectChocoPrice, is(75D));
    }
}