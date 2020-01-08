package ru.job4j.multithreading.synchronize;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenTransfer50FromUser1ToUser2ThenUser1BalanceIs50User2BalanceIs250() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);

        storage.transfer(1, 2, 50);
        assertThat(user1.getAmount(), is(50));
        assertThat(user2.getAmount(), is(250));
    }

    @Test
    public void whenAddUserToUserStorageResultIsTrue() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);

        assertTrue(storage.add(user));
    }

    @Test
    public void whenDeleteExistingUserFromUserStorageResultIsTrue() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);
        storage.add(user);

        assertTrue(storage.delete(user));
    }

    @Test
    public void whenDeleteNonexistentUserFromUserStorageResultIsFalse() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);

        assertFalse(storage.delete(user));
    }
}