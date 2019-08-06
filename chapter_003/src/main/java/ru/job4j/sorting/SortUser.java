package ru.job4j.sorting;

import ru.job4j.search.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Сортировки Person.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.08.2019
 * @version 1.0
 */
public class SortUser {

    /**
     * Сортировка пользователей Person по возрасту
     * с конвертацией из List в TreeSet.
     *
     * @param users List пользователей Person.
     * @return Отсортированный по возрасту TreeSet пользователей Person.
     */
    public Set<Person> sortAge(List<Person> users) {
        return new TreeSet<>(users);
    }

    /**
     * Сортировка пользователей Person по длине имени.
     *
     * @param users List пользователей Person.
     * @return Отсортированный по длине имени List пользователей Person.
     */
    public List<Person> sortNameLength(List<Person> users) {
        Comparator<Person> compareNameLength = new Comparator<>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        };
        users.sort(compareNameLength);
        return users;
    }

    /**
     * Сортировка по имени, потом по возрасту.
     * @param users List пользователей Person.
     * @return Отсортированный по имени, потом по возрасту, List пользователей Person.
     */
    public List<Person> sortAllFields(List<Person> users) {
        Comparator<Person> compareNameAndAge = new Comparator<>() {
            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result == 0 ? o1.getAge().compareTo(o2.getAge()) : result;
            }
        };
        users.sort(compareNameAndAge);
        return users;
    }
}
