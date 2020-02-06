package ru.job4j.crudservlet;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of Store that uses memory.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public enum MemoryStore implements Store {
    INSTANCE;
    private final AtomicInteger idCounter = new AtomicInteger();
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public void add(User model) {
        String id = String.valueOf(idCounter.incrementAndGet());
        model.setId(id);
        userMap.put(model.getId(), model);
    }

    @Override
    public void update(User model) {
        userMap.get(model.getId()).setName(model.getName());
    }

    @Override
    public void delete(User model) {
        userMap.remove(model.getId());
    }

    @Override
    public Collection<User> findAll() {
        return userMap.values();
    }

    @Override
    public String findById(User model) {
        return userMap.get(model.getId()).toString();
    }

    @Override
    public User getUserById(User model) {
        return userMap.get(model.getId());
    }

    @Override
    public boolean containsUser(User model) {
        return userMap.containsKey(model.getId());
    }

    @Override
    public boolean containsLogin(User model) {
        return userMap.values().stream().anyMatch(user ->
                model.getLogin().equals(user.getLogin()));
    }

    @Override
    public boolean containsEmail(User model) {
        return userMap.values().stream().anyMatch(user ->
                model.getEmail().equals(user.getEmail()));
    }
}