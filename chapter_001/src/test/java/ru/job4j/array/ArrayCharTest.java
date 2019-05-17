package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayCharTest {

    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar text = new ArrayChar("Hello, world!");
        boolean result = text.startWith("He");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar text = new ArrayChar("Hello, world!");
        boolean result = text.startWith("Hi");
        assertThat(result, is(false));
    }
}