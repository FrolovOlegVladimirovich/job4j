package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {
    private UserStore userStore = new UserStore(4);
    private User user1 = new User("1");
    private User user2 = new User("2");
    private User user3 = new User("3");
    private User user4 = new User("4");

    private RoleStore roleStore = new RoleStore(4);
    private Role role1 = new Role("1");
    private Role role2 = new Role("2");
    private Role role3 = new Role("3");
    private Role role4 = new Role("4");

    @Before
    public void addUsersAndRolesToStore() {
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        userStore.add(user4);

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        roleStore.add(role4);
    }

    @Test
    public void testAdd() {
        UserStore userExpected = new UserStore(4);
        userExpected.add(userStore.findById("1"));
        userExpected.add(userStore.findById("2"));
        userExpected.add(userStore.findById("3"));
        userExpected.add(userStore.findById("4"));

        RoleStore roleExpected = new RoleStore(4);
        roleExpected.add(roleStore.findById("1"));
        roleExpected.add(roleStore.findById("2"));
        roleExpected.add(roleStore.findById("3"));
        roleExpected.add(roleStore.findById("4"));

        assertThat(userStore, is(userExpected));
        assertThat(roleStore, is(roleExpected));
    }

    @Test
    public void testFindById() {
        Base userResult = userStore.findById("1");
        Base roleResult = roleStore.findById("1");

        assertThat(userResult, is(user1));
        assertThat(roleResult, is(role1));
    }

    @Test
    public void testDelete() {
        boolean userResultTrue = userStore.delete("3");
        boolean userResultFalse = userStore.delete("5");

        boolean roleResultTrue = roleStore.delete("3");
        boolean roleResultFalse = roleStore.delete("5");

        UserStore userExpected = new UserStore(3);
        RoleStore roleExpected = new RoleStore(3);

        userExpected.add(userStore.findById("1"));
        userExpected.add(userStore.findById("2"));
        userExpected.add(userStore.findById("4"));

        roleExpected.add(roleStore.findById("1"));
        roleExpected.add(roleStore.findById("2"));
        roleExpected.add(roleStore.findById("4"));

        assertThat(userResultTrue, is(true));
        assertThat(userResultFalse, is(false));
        assertThat(userStore, is(userExpected));

        assertThat(roleResultTrue, is(true));
        assertThat(roleResultFalse, is(false));
        assertThat(roleStore, is(roleExpected));
    }

    @Test
    public void testReplace() {
        UserStore userExpected = new UserStore(4);
        RoleStore roleExpected = new RoleStore(4);

        userExpected.add(userStore.findById("1"));
        userExpected.add(userStore.findById("2"));
        userExpected.add(userStore.findById("1"));
        userExpected.add(userStore.findById("4"));

        roleExpected.add(roleStore.findById("1"));
        roleExpected.add(roleStore.findById("2"));
        roleExpected.add(roleStore.findById("1"));
        roleExpected.add(roleStore.findById("4"));

        boolean userResultTrue = userStore.replace("3", user1);
        boolean userResultFalse = userStore.replace("10", user1);

        boolean roleResultTrue = roleStore.replace("3", role1);
        boolean roleResultFalse = roleStore.replace("10", role1);

        assertThat(userResultTrue, is(true));
        assertThat(userResultFalse, is(false));
        assertThat(userStore, is(userExpected));

        assertThat(roleResultTrue, is(true));
        assertThat(roleResultFalse, is(false));
        assertThat(roleStore, is(roleExpected));
    }
}