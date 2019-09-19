package ru.job4j.statistics;

import java.util.*;

/**
 * Анализирует разницу между начальным и измененным состоянием коллекции.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 19.09.2019
 * @version 1.0
 */
public class Analize {

    /**
     * Производит расчет разницы двух List.
     * @param previous List с начальным состоянием.
     * @param current List с конечным состоянием.
     * @return Info со статистикой об изменении коллекции.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> curr = new HashMap<>();
        for (User user : current) {
            curr.put(user.getId(), user);
        }
        for (User user : previous) {
            if (!curr.containsKey(user.getId())) {
                info.setDeleted(1);
            } else if (!curr.get(user.getId()).getName().equals(user.getName())) {
                info.setChanged(1);
            }
        }
        info.setAdded(current.size() - (previous.size() - info.getDeleted()));
        return info;
    }
}