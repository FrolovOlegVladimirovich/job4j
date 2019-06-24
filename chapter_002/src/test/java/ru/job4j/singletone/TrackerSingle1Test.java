package ru.job4j.singletone;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingle1Test {
    @Test
    public void whenTwoObjectsAreEqual() {
        Tracker trackerOne = TrackerSingle1.TRACKER.getTracker();
        Tracker trackerTwo = TrackerSingle1.TRACKER.getTracker();
        assertThat(trackerOne, is(trackerTwo));
    }
}
