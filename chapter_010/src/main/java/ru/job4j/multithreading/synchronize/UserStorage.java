package ru.job4j.multithreading.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Thread safe user storage.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> storage;

    public UserStorage() {
        this.storage = new HashMap<>();
    }

    public synchronized boolean add(User user) {
        storage.put(user.getId(), user);
        return storage.containsValue(user);
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        var userFrom = storage.get(fromId);
        var userTo = storage.get(toId);
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
    }
}