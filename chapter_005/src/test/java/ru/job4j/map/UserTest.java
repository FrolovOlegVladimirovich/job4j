package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    /**
     * Выведет в консоль hashcode объектов и значение:
     * {ru.job4j.map.User@13b6d03=oleg1, ru.job4j.map.User@f5f2bb7=oleg2}
     *
     * Не смотря на одинаковые значения полей в объектах Oleg1 и Oleg2,
     * hashcode у объектов разные.
     * Т.к. метод hashCode() в классе User не переопределен, то каждому новому экземпляру
     * класса User присваивается новый уникальный hashcode, не смотря на значение полей.
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