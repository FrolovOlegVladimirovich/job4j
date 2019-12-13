package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

public class SimpleMenu implements Menu {
    private List<Item> items = new ArrayList<>();

    public SimpleMenu() {
    }

    @Override
    public void add(Item item) {
        items.add(item);
    }

    @Override
    public void add(List<Item> itemList) {
        items.addAll(itemList);
    }

    @Override
    public void show() {
        for (Item item: items) {
            item.show();
        }
    }
}