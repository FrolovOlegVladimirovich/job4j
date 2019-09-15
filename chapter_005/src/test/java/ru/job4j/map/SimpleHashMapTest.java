package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SimpleHashMapTest {
    Calendar birthday = new GregorianCalendar();
    SimpleHashMap<User, String> map;
    User user1 = new User("user1", 1, birthday);
    User user2 = new User("user2", 1, birthday);
    User user3 = new User("user3", 1, birthday);
    User user4 = new User("user4", 1, birthday);
    User user5 = new User("user5", 1, birthday);
    User user6 = new User("user6", 1, birthday);
    User user7 = new User("user7", 1, birthday);
    User user8 = new User("user8", 1, birthday);
    User user9 = new User("user9", 1, birthday);
    User user10 = new User("user10", 1, birthday);
    User user11 = new User("user11", 1, birthday);
    User user12 = new User("user12", 1, birthday);
    User user13 = new User("user13", 1, birthday);
    User user14 = new User("user14", 1, birthday);
    User user15 = new User("user15", 1, birthday);

    @Before
    public void setUp() {
        birthday.set(1988, Calendar.FEBRUARY, 25);
        map = new SimpleHashMap<>();
    }

    @Test
    public void whenAddUserToMapResultShouldBeTrue() {
        boolean result = map.insert(user1, "user1");

        assertThat(result, is(true));
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenAddDifferentUserResultShouldBeTrue() {
        map.insert(user1, "user1");
        boolean result = map.insert(user2, "user2");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddTheSameUserToMapResultShouldBeFalse() {
        User oleg1 = new User("Oleg", 1, birthday);
        User oleg2 = new User("Oleg", 1, birthday);

        map.insert(oleg1, "Oleg");
        boolean result = map.insert(oleg2, "Oleg2");

        assertThat(result, is(false));
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenHashMapOverloadedCapacityShouldBe32() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");
        map.insert(user5, "5");
        map.insert(user6, "6");
        map.insert(user7, "7");
        map.insert(user8, "8");
        map.insert(user9, "9");
        map.insert(user10, "10");
        map.insert(user11, "11");
        map.insert(user12, "12");
        map.insert(user13, "13");
        map.insert(user14, "14");
        map.insert(user15, "15");

        if (map.getSize() < 12) {
            assertThat(map.getCapacity(), is(16));
        } else if (map.getSize() >= 12) {
            assertThat(map.getCapacity(), is(32));
        }
    }

    @Test
    public void whenAddTheSameNullKeyToMapResultShouldBeFalse() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        boolean result = map.insert(null, "Oleg");
        boolean result2 = map.insert(null, "NULL2");

        assertThat(result, is(true));
        assertThat(result2, is(false));
    }

    @Test
    public void whenGetExistingKeysReturnValues() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");

        assertThat(map.get(user2), is("2"));
        assertThat(map.get(user1), is("1"));
        assertThat(map.get(user3), is("3"));
    }

    @Test
    public void whenGetNonexistentKeyReturnNull() {
        map.get(user4);

        assertThat(map.get(user4), is(nullValue()));
    }

    @Test
    public void whenInsertNullKeyShouldGetResultNullKeyValue() {
        map.insert(null, "null");

        assertThat(map.get(null), is("null"));
    }

    @Test
    public void whenNoElementsInMapAndGetNullKeyResultIsNull() {
        assertThat(map.get(null), is(nullValue()));
    }

    @Test
    public void whenDeleteAllExistingElementsResultShouldBeTrueAndMapSize0() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");

        assertThat(map.delete(user2), is(true));
        assertThat(map.delete(user1), is(true));
        assertThat(map.delete(user3), is(true));
        assertThat(map.getSize(), is(0));
    }

    @Test
    public void whenDeleteNonexistentElementResultShouldBeFalse() {
        assertThat(map.delete(user1), is(false));
    }

    @Test
    public void whenDeleteNonexistentNullElementResultShouldBeFalse() {
        map.insert(user1, "1");

        assertThat(map.delete(null), is(false));
    }

    @Test
    public void whenDeleteExistingNullElementResultShouldBeTrue() {
        map.insert(user1, "1");
        map.insert(null, "null");

        assertThat(map.delete(null), is(true));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");
        map.insert(user5, "5");
        Iterator<SimpleHashMap.Node> it = map.iterator();

        it.next(); // user1
        it.next(); // user2
        it.next(); // user3
        it.next(); // user4
        it.next(); // user5
        it.next(); // NoSuchElementException
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");
        map.insert(user5, "5");
        Iterator<SimpleHashMap.Node> it = map.iterator();

        it.next();
        map.insert(user6, "6");
        it.next();
    }

    @Test
    public void whenNoElementsResultShouldBeFalse() {
        Iterator<SimpleHashMap.Node> it = map.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");
        map.insert(user5, "5");
        Iterator<SimpleHashMap.Node> it = map.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");
        map.insert(user5, "5");
        Iterator<SimpleHashMap.Node> it = map.iterator();

        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");
        map.insert(user5, "5");
        Iterator<SimpleHashMap.Node> it = map.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
        assertThat(it.next(), is(instanceOf(SimpleHashMap.Node.class)));
    }
}