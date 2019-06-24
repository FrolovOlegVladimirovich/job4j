package ru.job4j.singletone;

import ru.job4j.tracker.Tracker;

/**
 * Паттерн Singletone: реализация через enum (eager loading).
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 25.06.2019
 * @version 1.0
 */
public enum TrackerSingle1 {
    TRACKER;
    private final Tracker tracker = new Tracker();

    public Tracker getTracker() {
        return tracker;
    }
}