package ru.job4j.coffeemachine;

import org.junit.Test;
import java.util.Arrays;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ChangeTest {

    /**
     * Тест для проверки метода, возвращающего сдачу в виде массива монет разного номинала.
     */
    @Test
    public void testChanges() {
        Change change = new Change();
        int[] result = {10, 5, 1};
        assertThat(Arrays.toString(change.changes(50, 34)), is(Arrays.toString(result)));
    }

    @Test
    public void testChanges2() {
        Change change = new Change();
        int[] result = {10, 10, 10, 10, 10, 10, 2, 1};
        assertThat(Arrays.toString(change.changes(100, 37)), is(Arrays.toString(result)));
    }

    @Test
    public void testChanges3() {
        Change change = new Change();
        int[] result = {10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(Arrays.toString(change.changes(100, 42)), is(Arrays.toString(result)));
    }

    @Test
    public void testChanges4() {
        Change change = new Change();
        int[] result = {10, 5};
        assertThat(Arrays.toString(change.changes(50, 35)), is(Arrays.toString(result)));
    }

    /**
     * Если покупка без сдачи.
     */
    @Test
    public void testChangesWhileNoChange() {
        Change change = new Change();
        int[] result = {};
        assertThat(Arrays.toString(change.changes(50, 50)), is(Arrays.toString(result)));
    }
}
