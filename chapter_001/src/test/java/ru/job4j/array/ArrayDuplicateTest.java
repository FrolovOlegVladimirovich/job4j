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

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicates2() {
        ArrayDuplicate data = new ArrayDuplicate();
        String[] text = {"Привет", "Привет", "Привет", "Привет", "Привет", "Привет"};
        String[] result = data.remove(text);
        String[] expect = {"Привет"};
        assertThat(result, is(expect));

    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicates3() {
        ArrayDuplicate data = new ArrayDuplicate();
        String[] text = {"Привет"};
        String[] result = data.remove(text);
        String[] expect = {"Привет"};
        assertThat(result, is(expect));

    }
}
