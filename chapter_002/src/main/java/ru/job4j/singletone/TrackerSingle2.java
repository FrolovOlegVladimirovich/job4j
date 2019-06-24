package ru.job4j.singletone;

import ru.job4j.tracker.Tracker;

/**
 * Паттерн Singletone: реализация через static final field (eager loading).
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 25.06.2019
 * @version 1.0
 */
public class TrackerSingle2 {
    private static final Tracker TRACKER = new Tracker();

    private TrackerSingle2() {
    }

    public static Tracker getTracker() {
        return TRACKER;
    }
}
