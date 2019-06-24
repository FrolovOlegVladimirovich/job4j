package ru.job4j.singletone;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingle3Test {
    @Test
    public void henTwoObjectsAreEqual() {
        Tracker trackerOne = TrackerSingle3.getTracker();
        Tracker trackerTwo = TrackerSingle3.getTracker();
        assertThat(trackerOne, is(trackerTwo));
    }
}
