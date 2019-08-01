package ru.job4j.search;

/**
 * Пользователь для телефонного справочника.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 01.08.2019
 * @version 1.0
 */
public class Person {
    private String name;
    private String surename;
    private String phone;
    private String adress;

    public Person(String name, String surename, String phone, String adress) {
        this.name = name;
        this.surename = surename;
        this.phone = phone;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public String getSurename() {
        return surename;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }
}
