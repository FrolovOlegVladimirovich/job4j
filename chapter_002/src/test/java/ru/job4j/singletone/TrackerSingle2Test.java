package ru.job4j.singletone;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingle2Test {
    @Test
    public void whenTwoObjectsAreEqual() {
        Tracker trackerOne = TrackerSingle2.getTracker();
        Tracker trackerTwo = TrackerSingle2.getTracker();
        assertThat(trackerOne, is(trackerTwo));
    }
}
