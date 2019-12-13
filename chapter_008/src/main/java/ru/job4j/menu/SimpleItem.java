package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu item.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class SimpleItem implements Item  {
    private String name;
    /**
     * Shows menu depth level.
     */
    private String prefixSymbol = "----";
    private String prefix = "";
    private List<Item> items = new ArrayList<>();

    public SimpleItem(String name) {
        this.name = name;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setPrefixSymbol(String prefixSymbol) {
        this.prefixSymbol = prefixSymbol;
    }

    @Override
    public void add(Item item) {
        setPrefix(item);
        items.add(item);
    }

    private void setPrefix(Item item) {
        SimpleItem simpleItem = (SimpleItem) item;
        StringBuilder build = new StringBuilder(prefix).insert(0, prefixSymbol);
        String itemPrefix = build.toString();
        simpleItem.setPrefix(itemPrefix);
    }

    @Override
    public void add(List<Item> itemList) {
        items.addAll(itemList);
    }

    /**
     * Do some action for future use.
     */
    @Override
    public void action() {
        System.out.printf("%s - %s", name, "Do some action");
    }

    @Override
    public void show() {
        System.out.println(prefix + name);
        if (!items.isEmpty()) {
            for (Item item : items) {
                item.show();
            }
        }
    }
}