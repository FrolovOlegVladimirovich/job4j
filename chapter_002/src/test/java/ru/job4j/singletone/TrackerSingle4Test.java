package ru.job4j.singletone;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingle4Test {
    @Test
    public void whenTwoObjectsAreEqual() {
        Tracker trackerOne = TrackerSingle4.getTracker();
        Tracker trackerTwo = TrackerSingle4.getTracker();
        assertThat(trackerOne, is(trackerTwo));
    }
}
