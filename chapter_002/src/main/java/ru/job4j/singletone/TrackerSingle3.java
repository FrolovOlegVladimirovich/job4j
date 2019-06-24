package ru.job4j.singletone;

import ru.job4j.tracker.Tracker;

/**
 * Паттерн Singletone: реализация через static field (lazy loading).
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 25.06.2019
 * @version 1.0
 */
public class TrackerSingle3 {
    private static Tracker tracker;

    private TrackerSingle3() {
    }

    public static Tracker getTracker() {
        if (tracker == null) {
            tracker = new Tracker();
        }
        return tracker;
    }
}
