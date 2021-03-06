package ru.job4j.crudservlet;

import java.util.Collection;
import java.util.Map;

/**
 * Database for storing user.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface Store {
    User add(User model);

    void update(User model);

    void delete(User model);

    Collection<User> findAll();

    Map<String, Collection<String>> findLocations();

    String findById(User model);

    User getUserById(User model);

    boolean containsUser(User model);

    boolean containsLogin(User model);

    boolean containsEmail(User model);

    User isCredential(User model);
}