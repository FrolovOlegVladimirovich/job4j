package ru.job4j.sorting;

import org.junit.Test;
import ru.job4j.search.Person;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест сортировки SortUser.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 06.08.2019
 * @version 1.0
 */
public class SortUserTest {

    /**
     * Тест сортировки пользователей по возрасту.
     */
    @Test
    public void testSortingByAgeAfterSortingByName() {
        SortUser sortedUsers = new SortUser();
        List<Person> persons = List.of(
                new Person("Petr", "35"),
                new Person("Oleg", "31"),
                new Person("Vasily", "25"),
                new Person("Olga", "29"),
                new Person("Vitaly", "38"),
                new Person("Vladimir", "31"),
                new Person("Nikolay", "44"),
                new Person("Polina", "7"),
                new Person("Alena", "31")
        );
        Set<Person> result = sortedUsers.sortAge(persons);
        Set<Person> expected = Set.of(
                new Person("Polina", "7"),
                new Person("Vasily", "25"),
                new Person("Olga", "29"),
                new Person("Alena", "31"),
                new Person("Oleg", "31"),
                new Person("Vladimir", "31"),
                new Person("Petr", "35"),
                new Person("Vitaly", "38"),
                new Person("Nikolay", "44")
        );
        assertThat(result, is(expected));
    }

    /**
     * Тест сортировки пользователей по длине имени.
     */
    @Test
    public void testSortingByNameLength() {
        SortUser users = new SortUser();
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Oleg"),
                new Person("Vsevolod"),
                new Person("Eva"),
                new Person("Alena"),
                new Person("Nikolay"),
                new Person("Viktoriya"),
                new Person("Polina")

        ));
        List<Person> result = users.sortNameLength(persons);
        List<Person> expected = List.of(
                new Person("Eva"),
                new Person("Oleg"),
                new Person("Alena"),
                new Person("Polina"),
                new Person("Nikolay"),
                new Person("Vsevolod"),
                new Person("Viktoriya")
        );
        assertThat(result, is(expected));
    }

    /**
     * Тест сортировки по имени, потом по возрасту.
     */
    @Test
    public void testSortingByNameAfterSortingByAge() {
        SortUser users = new SortUser();
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Oleg", "31"),
                new Person("Vsevolod", "38"),
                new Person("Viktoriya", "45"),
                new Person("Eva", "31"),
                new Person("Viktoriya", "15"),
                new Person("Alena", "25"),
                new Person("Oleg", "20"),
                new Person("Nikolay", "44"),
                new Person("Viktoriya", "31"),
                new Person("Polina", "7")

        ));
        List<Person> result = users.sortAllFields(persons);
        List<Person> expected = List.of(
                new Person("Alena", "25"),
                new Person("Eva", "31"),
                new Person("Nikolay", "44"),
                new Person("Oleg", "20"),
                new Person("Oleg", "31"),
                new Person("Polina", "7"),
                new Person("Viktoriya", "15"),
                new Person("Viktoriya", "31"),
                new Person("Viktoriya", "45"),
                new Person("Vsevolod", "38")
        );
        assertThat(result, is(expected));
    }
}
