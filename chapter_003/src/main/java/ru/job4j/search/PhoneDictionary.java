package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Телефонный справочник на базе ArrayList.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 01.08.2019
 * @version 1.0
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    /**
     * Добавляет пользователя в справочник.
     * @param person Пользователь.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Поиск всех пользователей, которые содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список найденных пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person index : this.persons) {
            if (index.getName().contains(key)
                    || index.getSurename().contains(key)
                    || index.getPhone().contains(key)
                    || index.getAdress().contains(key)) {
                result.add(index);
            }
        }
        return result;
    }
}
