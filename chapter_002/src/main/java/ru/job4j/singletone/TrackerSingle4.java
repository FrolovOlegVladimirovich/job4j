package ru.job4j.singletone;

import ru.job4j.tracker.Tracker;

/**
 * Паттерн Singletone: реализация через private static final class (lazy loading).
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 25.06.2019
 * @version 1.0
 */
public class TrackerSingle4 {
    private TrackerSingle4() {
    }

    private static final class Holder {
        private static final Tracker TRACKER = new Tracker();
    }

    public static Tracker getTracker() {
        return Holder.TRACKER;
    }
}
