package ru.job4j.sorting;

import ru.job4j.search.Person;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Сортировка Person по возрасту.
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
    public Set<Person> sort(List<Person> users) {
        return new TreeSet<>(users);
    }
}
