package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    /**
     * Выведет в консоль hashcode объекта и значение:
     * {ru.job4j.map.User@4e5bb0e9=oleg2}
     *
     * Hashcodes объектов oleg1 и oleg2 одинаковы, т.к.
     * метод hashCode() в классе User переопределен.
     * При сравнении объектов через equals(), они также равны,
     * т.к. метод equals() в классе User переопределен.
     *
     * При добавлении в HashMap, сравнив объекты по hashcode получаем true.
     * Далее объекты сравниваются через equals(), т.к. hashcode
     * может быть не уникальным (т.е. одинаковым у разных объектов).
     *
     * В результате объект oleg2, добавляется в HashMap, удалив из
     * HashMap объект oleg1, т.к. oleg2 и oleg1 одинаковы и по hashCode(),
     * и по equals().
     *
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