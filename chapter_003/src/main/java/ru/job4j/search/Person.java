package ru.job4j.search;

import java.util.Objects;

/**
 * Пользователь для телефонного справочника.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 01.08.2019
 * @version 1.0
 */
public class Person implements Comparable<Person> {
    private String id;
    private String name;
    private String age;
    private String surename;
    private String phone;
    private String adress;

    public Person(String name, String surename, String phone, String adress) {
        this.name = name;
        this.surename = surename;
        this.phone = phone;
        this.adress = adress;
    }

    public Person(String id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int compareTo(Person o) {
        return this.age.compareTo(o.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Person{"
                +
                "name='" + name + '\''
                +
                ", age='" + age + '\''
                +
                '}';

    }
}
