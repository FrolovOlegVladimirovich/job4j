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

    @Test
    public void testSortingByAgeThenSortingByName() {
        SortUser sortedUsers = new SortUser();
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Petr", "35"),
                new Person("Oleg", "31"),
                new Person("Vasily", "25"),
                new Person("Olga", "29"),
                new Person("Vitaly", "38"),
                new Person("Vladimir", "31"),
                new Person("Nikolay", "44"),
                new Person("Polina", "7"),
                new Person("Alena", "31")
        ));
        Set<Person> result = sortedUsers.sort(persons);
        Set<Person> expected = new TreeSet<>(Arrays.asList(
                new Person("Polina", "7"),
                new Person("Vasily", "25"),
                new Person("Olga", "29"),
                new Person("Alena", "31"),
                new Person("Oleg", "31"),
                new Person("Vladimir", "31"),
                new Person("Petr", "35"),
                new Person("Vitaly", "38"),
                new Person("Nikolay", "44")
        ));
        assertThat(result, is(expected));
    }
}
