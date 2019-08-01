package ru.job4j.search;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест PhoneDictionary.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 01.08.2019
 * @version 1.0
 */
public class PhoneDictionaryTest {
    PhoneDictionary phones = new PhoneDictionary();
    @Before
    public void newDictAndPersons() {
        this.phones.add(
                new Person("Petr", "Arsentev", "+79990009900", "Russia, Bryansk")
        );
        this.phones.add(
                new Person("Oleg", "Frolov", "+79099520000", "Russia, Moscow")
        );
        this.phones.add(
                new Person("Joe", "Hanks", "+16318726366", "USA, New York")
        );
        this.phones.add(
                new Person("Olga", "Petrova", "+79057051134", "Russia, St. Petersburg")
        );
    }

    /**
     * Тест на поиск по имени.
     */
    @Test
    public void whenFindByName() {
        List<Person> persons = this.phones.find("Oleg");
        assertThat(persons.iterator().next().getSurename(), is("Frolov"));
    }

    /**
     * Тест на поиск по фамилии.
     */
    @Test
    public void whenFindBySurename() {
        List<Person> persons = this.phones.find("Hanks");
        assertThat(persons.iterator().next().getName(), is("Joe"));
    }

    /**
     * Тест на поиск по номеру телефона.
     */
    @Test
    public void whenFindByPhone() {
        List<Person> persons = this.phones.find("9057051134");
        assertThat(persons.iterator().next().getSurename(), is("Petrova"));
    }

    /**
     * Тест на поиск по адресу.
     */
    @Test
    public void whenFindByAdress() {
        List<Person> persons = this.phones.find("Bryansk");
        assertThat(persons.iterator().next().getSurename(), is("Arsentev"));
    }
}
