package ru.job4j.list;

import org.junit.Test;
import ru.job4j.search.Person;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест конвертера ArrayList в различные типы данных.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.08.2019
 * @version 1.0
 */
public class ConvertListTest {

    /**
     * Тесты конвертации ArrayList в двухмерный массив.
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen9() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7, 8), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when9ElementsThen9() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when7ElementsThen8() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7), 2);
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen8() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7, 8), 2);
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when9ElementsThen10() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 2);
        int[][] expect = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when9ElementsThen10With5Rows() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 5);
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {9, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen10With5Rows() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7, 8), 5);
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {0, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen8With4Rows() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7, 8), 4);
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
        };
        assertThat(result, is(expect));
    }

    /**
     * Тесты конвертации ArrayList int[] в ArrayList Integer.
     */
    @Test
    public void whenListWith2Arrays10ElementsThenListWith10IntegerElements() {
        ConvertList list = new ConvertList();
        List<Integer> result = list.toList(List.of(
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9}
        ));
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(result, is(expect));
    }

    @Test
    public void whenListWith5Arrays10ElementsThenListWith10IntegerElements() {
        ConvertList list = new ConvertList();
        List<Integer> result = list.toList(List.of(
                new int[]{1, 2},
                new int[]{3, 4, 5},
                new int[]{6, 7, 8, 9, 10},
                new int[]{11},
                new int[]{12, 13}
        ));
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertThat(result, is(expect));
    }

    /**
     * Тест конвертации List Person в HashMap Integer, Person.
     */
    @Test
    public void whenListWith3PersonsThenMapWith3Elements() {
        ConvertList list = new ConvertList();
        Person one = new Person("111", "Petr", "Russia, Bryansk");
        Person two = new Person("123", "Oleg", "Russia, Moscow");
        Person three = new Person("222", "Joe", "USA, New York");
        HashMap<Integer, Person> result = list.toMap(List.of(
                one,
                two,
                three
        ));
        HashMap<Integer, Person> expect = new HashMap<>();
        expect.put(111, one);
        expect.put(123, two);
        expect.put(222, three);
        assertThat(result, is(expect));
    }
}
