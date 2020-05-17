package ru.job4j.crudservlet;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class ValidateServiceTest {
    private final ValidateService validateService = ValidateService.INSTANCE;

    @Test
    public void whenAddUserWithNullField() {
        User user = new User();
        user.setName("Name");
        user.setLogin("Login");

        String result = validateService.add(user);
        String expect = String.format("Unable to register user. Required fields:"
                + " name, login, email. Empty fields:%nemail");

        assertThat(result, is(expect));
    }

    @Test
    public void whenAddUserWithEmptyField() {
        User user = new User();
        user.setName("Name");
        user.setLogin("Login");
        user.setEmail("");

        String result = validateService.add(user);
        String expect = String.format("Unable to register user. Required fields:"
                + " name, login, email. Empty fields:%nemail");

        assertThat(result, is(expect));
    }

    @Test
    public void whenAddUserWithTheSameLogin() {
        String login = "Login";
        User user1 = new User();
        user1.setName("Name");
        user1.setLogin(login);
        user1.setEmail("mail@mail.com");
        User user2 = new User();
        user2.setName("Name");
        user2.setLogin(login);
        user2.setEmail("mail2@mail.com");

        validateService.add(user1);
        String result = validateService.add(user2);
        String expect = String.format("User with login \"%s\" already exists."
                + " Try a different login.%n", login);
        validateService.delete(user1);

        assertThat(result, is(expect));
    }

    @Test
    public void whenAddUserWithTheSameEmail() {
        String email = "mail@mail.com";
        User user1 = new User();
        user1.setName("Name");
        user1.setLogin("Login");
        user1.setEmail(email);
        User user2 = new User();
        user2.setName("Name");
        user2.setLogin("Login2");
        user2.setEmail(email);

        validateService.add(user1);
        String result = validateService.add(user2);
        String expect = String.format("User with e-mail \"%s\" already exists. "
                + "Try a different email.%n", email);
        validateService.delete(user1);

        assertThat(result, is(expect));
    }

    @Test
    public void whenSuccessfullyAddedUser() {
        String login = "TestLogin";
        User user = new User();
        user.setName("TestName");
        user.setLogin(login);
        user.setEmail("testmail@mail.com");

        String result = validateService.add(user);
        String expect = String.format("User %s was successfully created with ID %s.",
                login, user.getId());
        validateService.delete(user);

        assertThat(result, is(expect));
    }

    @Test
    public void whenUpdateUserWithNullField() {
        User user1 = new User();
        user1.setName("Name");
        user1.setLogin("Login");
        user1.setEmail("testmail@mail.com");
        validateService.add(user1);
        User model = new User();
        model.setId(user1.getId());

        String result = validateService.update(model);
        String expect = String.format("Unable to update user. Required fields: "
                + "id, name. Empty fields:%nname");
        validateService.delete(user1);

        assertThat(result, is(expect));
    }

    @Test
    public void whenUpdateUserWithEmptyField() {
        User user1 = new User();
        user1.setName("Name");
        user1.setLogin("Login");
        user1.setEmail("testmail@mail.com");
        validateService.add(user1);
        User model = new User();
        model.setId(user1.getId());
        model.setName("");

        String result = validateService.update(model);
        String expect = String.format("Unable to update user. Required fields:"
                + " id, name. Empty fields:%nname");
        validateService.delete(user1);

        assertThat(result, is(expect));
    }

    @Test
    public void whenSuccessfullyUpdatedUser() {
        User user1 = new User();
        user1.setName("Name");
        user1.setLogin("Login");
        user1.setEmail("testmail@mail.com");
        validateService.add(user1);
        User model = new User();
        String newName = "NewName";
        model.setId(user1.getId());
        model.setName(newName);

        String result = validateService.update(model);
        String expect = String.format("User ID %s was successfully changed name to %s.",
                user1.getId(), newName);
        validateService.delete(user1);

        assertThat(result, is(expect));
    }

    @Test
    public void whenUpdateUserWithTheSameName() {
        User user1 = new User();
        String name = "Name";
        user1.setName(name);
        user1.setLogin("Login");
        user1.setEmail("testmail@mail.com");
        validateService.add(user1);
        User model = new User();
        model.setId(user1.getId());
        model.setName(name);

        String result = validateService.update(model);
        String expect = "User names are the same. Try a different name.";
        validateService.delete(user1);

        assertThat(result, is(expect));
    }

    @Test
    public void whenUpdateUserWithNonexistentId() {
        User model = new User();
        model.setId("100500");
        model.setName("NewName");

        String result = validateService.update(model);
        String expect = String.format("User with ID %s doesn't exist. Try another id.",
                model.getId());

        assertThat(result, is(expect));
    }

    @Test
    public void whenDeleteUserWithNullIdField() {
        User model = new User();

        String result = validateService.delete(model);
        String expect = "Unable to delete user. Required fields: id.";

        assertThat(result, is(expect));
    }

    @Test
    public void whenDeleteUserWithEmptyIdField() {
        User model = new User();
        model.setId("");

        String result = validateService.delete(model);
        String expect = "Unable to delete user. Required fields: id.";

        assertThat(result, is(expect));
    }

    @Test
    public void whenDeleteUserWithNonexistentId() {
        User model = new User();
        model.setId("100500");

        String result = validateService.delete(model);
        String expect = String.format("User with ID %s doesn't exist. Try another id.",
                model.getId());

        assertThat(result, is(expect));
    }

    @Test
    public void whenSuccessfullyDeletedUser() {
        User user1 = new User();
        user1.setName("Name");
        user1.setLogin("Login");
        user1.setEmail("testmail@mail.com");
        validateService.add(user1);
        User model = new User();
        model.setId(user1.getId());

        String result = validateService.delete(model);
        String expect = String.format("User ID %s was successfully removed", model.getId());

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindUserWithNullIdField() {
        User model = new User();

        String result = validateService.findById(model);
        String expect = "Unable to find user. Required fields: id.";

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindUserWithEmptyIdField() {
        User model = new User();
        model.setId("");

        String result = validateService.findById(model);
        String expect = "Unable to find user. Required fields: id.";

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindUserWithNonexistentId() {
        User model = new User();
        model.setId("100500");

        String result = validateService.findById(model);
        String expect = String.format("User with ID %s doesn't exist. Try another id.",
                model.getId());

        assertThat(result, is(expect));
    }

    @Test
    public void whenSuccessfullyFoundUserById() {
        User user1 = new User();
        user1.setName("Name");
        user1.setLogin("Login");
        user1.setEmail("testmail@mail.com");
        validateService.add(user1);
        User model = new User();
        model.setId(user1.getId());

        String result = validateService.findById(model);
        String expect = user1.toString();
        validateService.delete(user1);

        assertThat(result, is(expect));
    }
}