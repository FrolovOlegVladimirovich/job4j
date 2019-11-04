package ru.job4j.io.mail;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MergeUsersTest {
    @Test
    public void when5UsersMergedInto2User() {
        Set<User> users = new HashSet<>(List.of(
                new User("user1", new HashSet<>(List.of(
                        "xxx@ya.ru",
                        "foo@gmail.com",
                        "lol@mail.ru"))),
                new User("user2", new HashSet<>(List.of(
                        "foo@gmail.com",
                        "ups@pisem.net"))),
                new User("user3", new HashSet<>(List.of(
                        "xyz@pisem.net",
                        "vasya@pupkin.com"))),
                new User("user4", new HashSet<>(List.of(
                        "ups@pisem.net",
                        "aaa@bbb.ru"))),
                new User("user5", new HashSet<>(List.of(
                        "xyz@pisem.net")))
        ));

        Set<User> result = new MergeUsers().byTheSameEmails(users);
        Set<User> expect = new HashSet<>(List.of(
                new User("user1", new HashSet<>(List.of(
                        "xxx@ya.ru",
                        "foo@gmail.com",
                        "lol@mail.ru",
                        "ups@pisem.net",
                        "aaa@bbb.ru"))),
                new User("user3", new HashSet<>(List.of(
                        "xyz@pisem.net",
                        "vasya@pupkin.com")))
        ));

        assertThat(result, is(expect));
    }

    @Test
    public void when5UsersMergedInto3User() {
        Set<User> users = new HashSet<>(List.of(
                new User("user1", new HashSet<>(List.of(
                        "xxx@ya.ru",
                        "foo@gmail.com",
                        "lol@mail.ru"))),
                new User("user2", new HashSet<>(List.of(
                        "foo@gmail.com",
                        "ups@pisem.net"))),
                new User("user3", new HashSet<>(List.of(
                        "vasya@pupkin.com"))),
                new User("user4", new HashSet<>(List.of(
                        "ups@pisem.net",
                        "aaa@bbb.ru"))),
                new User("user5", new HashSet<>(List.of(
                        "xyz@pisem.net",
                        "test@test.ru"))),
                new User("user4", new HashSet<>(List.of(
                        "test@test.ru")))
        ));

        Set<User> result = new MergeUsers().byTheSameEmails(users);
        Set<User> expect = new HashSet<>(List.of(
                new User("user1", new HashSet<>(List.of(
                        "xxx@ya.ru",
                        "foo@gmail.com",
                        "lol@mail.ru",
                        "ups@pisem.net",
                        "aaa@bbb.ru"))),
                new User("user4", new HashSet<>(List.of(
                        "xyz@pisem.net",
                        "test@test.ru"))),
                new User("user3", new HashSet<>(List.of(
                        "vasya@pupkin.com")))
        ));

        assertThat(result, is(expect));
    }

    @Test
    public void whenAllUsersUniqueResultIsAllUsers() {
        Set<User> users = new HashSet<>(List.of(
                new User("user1", new HashSet<>(List.of(
                        "xxx@ya.ru"))),
                new User("user2", new HashSet<>(List.of(
                        "foo@gmail.com"))),
                new User("user3", new HashSet<>(List.of(
                        "vasya@pupkin.com"))),
                new User("user4", new HashSet<>(List.of(
                        "aaa@bbb.ru"))),
                new User("user5", new HashSet<>(List.of(
                        "xyz@pisem.net"))),
                new User("user4", new HashSet<>(List.of(
                        "test@test.ru")))
        ));

        Set<User> result = new MergeUsers().byTheSameEmails(users);
        Set<User> expect = new HashSet<>(List.of(
                new User("user1", new HashSet<>(List.of(
                        "xxx@ya.ru"))),
                new User("user2", new HashSet<>(List.of(
                        "foo@gmail.com"))),
                new User("user3", new HashSet<>(List.of(
                        "vasya@pupkin.com"))),
                new User("user4", new HashSet<>(List.of(
                        "aaa@bbb.ru"))),
                new User("user5", new HashSet<>(List.of(
                        "xyz@pisem.net"))),
                new User("user4", new HashSet<>(List.of(
                        "test@test.ru")))
        ));

        assertThat(result, is(expect));
    }
}