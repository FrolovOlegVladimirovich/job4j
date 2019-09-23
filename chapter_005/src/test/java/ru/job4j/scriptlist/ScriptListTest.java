package ru.job4j.scriptlist;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тест списока скриптов и их зависимостей для класса ScriptList.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 23.09.2019
 * @version 1.0
 */
public class ScriptListTest {
    ScriptList scriptList = new ScriptList();
    Map<Integer, List<Integer>> scripts = new HashMap<>();
    List<Integer>  dependOne = List.of(2, 3);
    List<Integer>  dependTwo = List.of(4);
    List<Integer>  dependThree = List.of(4, 5);
    List<Integer>  dependFour = new ArrayList<>();
    List<Integer>  dependFive = new ArrayList<>();

    @Before
    public void setUp() {
        scripts.put(1, dependOne);
        scripts.put(2, dependTwo);
        scripts.put(3, dependThree);
        scripts.put(4, dependFour);
        scripts.put(5, dependFive);
    }

    @Test
    public void whenStartScript1ResultIs23445() {
        List<Integer> expected = List.of(2, 3, 4, 4, 5);
        List<Integer> result = scriptList.load(scripts, 1);

        assertThat(result, is(expected));
    }

    @Test
    public void whenStartScript2ResultIs4() {
        List<Integer> expected = List.of(4);
        List<Integer> result = scriptList.load(scripts, 2);

        assertThat(result, is(expected));
    }

    @Test
    public void whenStartScript3ResultIs45() {
        List<Integer> expected = List.of(4, 5);
        List<Integer> result = scriptList.load(scripts, 3);

        assertThat(result, is(expected));
    }

    @Test
    public void whenStartScript4ReturnNoElements() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> result = scriptList.load(scripts, 4);

        assertThat(result, is(expected));
    }

    @Test
    public void whenStartScript5ReturnNoElements() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> result = scriptList.load(scripts, 5);

        assertThat(result, is(expected));
    }

    @Test
    public void whenStartScript2ResultIs13544() {
        List<Integer>  dependOne = List.of(3);
        List<Integer>  dependTwo = List.of(1);
        List<Integer>  dependThree = List.of(5, 4);
        List<Integer>  dependFour = new ArrayList<>();
        List<Integer>  dependFive = List.of(4);

        scripts.put(1, dependOne);
        scripts.put(2, dependTwo);
        scripts.put(3, dependThree);
        scripts.put(4, dependFour);
        scripts.put(5, dependFive);

        List<Integer> expected = List.of(1, 3, 5, 4, 4);
        List<Integer> result = scriptList.load(scripts, 2);

        assertThat(result, is(expected));
    }
}