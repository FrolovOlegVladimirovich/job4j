package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тест анализа изменений двух List.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 19.09.2019
 * @version 1.0
 */
public class AnalizeTest {
    List<User> prev;
    List<User> curr;
    Analize stats;
    final User oleg = new User(1, "Oleg");
    final User petr = new User(2, "Petr");
    final User andrey = new User(3, "Andrey");
    final User roman = new User(4, "Roman");
    final User olga = new User(5, "Olga");
    final User petrArsentev = new User(2, "Petr Arsentev");
    final User romanIlin = new User(4, "Roman Ilin");
    final User sergey = new User(6, "Sergey");
    final User polina = new User(7, "Polina");

    @Before
    public void setUp() {
        prev = new ArrayList<>();
        prev.add(oleg);
        prev.add(petr);
        prev.add(andrey);
        prev.add(roman);
        prev.add(olga);
        curr = new ArrayList<>();
        stats = new Analize();
    }

    @Test
    public void whenChanged2ElementsChangeResult2() {
        curr.add(oleg);
        curr.add(petrArsentev);
        curr.add(andrey);
        curr.add(romanIlin);
        curr.add(olga);

        Info info = stats.diff(prev, curr);

        assertThat(info.getChanged(), is(2));
        assertThat(info.getAdded(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenNoAddsNoChangesNoDeletesResults0() {
        curr.add(oleg);
        curr.add(petr);
        curr.add(andrey);
        curr.add(roman);
        curr.add(olga);

        Info info = stats.diff(prev, curr);

        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenAdded2ElementsAddsResult2() {
        curr.add(oleg);
        curr.add(petr);
        curr.add(andrey);
        curr.add(roman);
        curr.add(olga);
        curr.add(sergey);
        curr.add(polina);

        Info info = stats.diff(prev, curr);

        assertThat(info.getAdded(), is(2));
        assertThat(info.getDeleted(), is(0));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenDeleted3ElementsDeletedResult3() {
        curr.add(petr);
        curr.add(andrey);

        Info info = stats.diff(prev, curr);

        assertThat(info.getDeleted(), is(3));
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenDeleted2Added1Changed2ElementsResult212() {
        curr.add(petrArsentev);
        curr.add(andrey);
        curr.add(romanIlin);
        curr.add(sergey);

        Info info = stats.diff(prev, curr);

        assertThat(info.getDeleted(), is(2));
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(2));
    }
}