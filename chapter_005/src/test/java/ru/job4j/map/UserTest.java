package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    /**
     * Выведет в консоль hashcode объектов и значение:
     * {ru.job4j.map.User@4b6995df=oleg2, ru.job4j.map.User@5474c6c=oleg1}
     * true
     *
     * Hashcodes объектов oleg1 и oleg2 разные, т.к.
     * метод hashCode() в классе User не переопределен.
     *
     * Т.к. в классе User переопределен только метод equals(),
     * то при сравнении объектов результат будет true.
     *
     * В HashMap оба объекта будут добавлены успешно, т.к.
     * hashcodes объектов разные. По логике работы HashMap:
     * если hashcodes объектов разные, значит
     * объекты 100% разные.
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
        System.out.println(oleg1.equals(oleg2));
    }
}