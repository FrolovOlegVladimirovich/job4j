package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    /**
     * Выведет в консоль hashcode объектов и значение:
     * {ru.job4j.map.User@4e08e4c8=oleg1, ru.job4j.map.User@4e08e4c8=oleg2}
     *
     * Теперь hashcodes объектов oleg1 и oleg2 равны, т.к.
     * метод hashCode() в классе User был переопределен
     * с учетом значений полей name, children и birthday.
     *
     * Т.к. значения полей одинаковы в обоих объектах, то hashcodes объектов также равны.
     */
    @Test
    public void userTest() {
        Calendar birthdayOleg = new GregorianCalendar();
        birthdayOleg.set(1988, Calendar.FEBRUARY, 25);

        User oleg1 = new User("Oleg", 1, birthdayOleg);
        User oleg2 = new User("Oleg", 1, birthdayOleg);

        Map<User, Object> map = new HashMap<>();
        map.put(oleg1, "oleg1");
        map.put(oleg2, "oleg2");


        System.out.println(map);
    }
}