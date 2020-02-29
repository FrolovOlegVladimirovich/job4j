package ru.job4j.crudservlet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StoreStub implements Store {
    private final Map<String, User> store = new HashMap<>();

    @Override
    public User add(User model) {
        model.setId(model.getId());
        this.store.put(model.getId(), model);
        return model;
    }

    @Override
    public void update(User model) {
        store.get(model.getId()).setName(model.getName());
    }

    @Override
    public void delete(User model) {
        store.remove(model.getId());
    }

    @Override
    public Collection<User> findAll() {
        return store.values();
    }

    @Override
    public String findById(User model) {
        return getUserById(model).toString();
    }

    @Override
    public User getUserById(User model) {
        return store.get(model.getId());
    }

    @Override
    public boolean containsUser(User model) {
        return store.containsKey(model.getId());
    }

    @Override
    public boolean containsLogin(User model) {
        return store.values().stream().anyMatch(user ->
                model.getLogin().equals(user.getLogin()));
    }

    @Override
    public boolean containsEmail(User model) {
        return store.values().stream().anyMatch(user ->
                model.getEmail().equals(user.getEmail()));
    }

    @Override
    public User isCredential(User model) {
        User user = null;
        String login = model.getLogin();
        String password = model.getPassword();
        for (User u : store.values()) {
            if (login.equals(u.getLogin()) && password.equals(u.getPassword())) {
                user = new User();
                user.setId(u.getId());
                user.setRole(u.getRole());
                user.setName(u.getName());
                user.setLogin(login);
                break;
            }
        }
        return user;
    }
}