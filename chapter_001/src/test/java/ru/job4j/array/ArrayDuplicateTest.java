package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicates() {
        ArrayDuplicate data = new ArrayDuplicate();
        String[] text = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] result = data.remove(text);
        String[] expect = {"Привет", "Мир", "Супер"};
        assertThat(result, is(expect));

    }
}
