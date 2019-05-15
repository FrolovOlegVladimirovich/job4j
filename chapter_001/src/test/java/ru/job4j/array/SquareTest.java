package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {

    @Test
    /**
     * Тест: размер массива 3.
     */
    public void whenBound3Then1and4and9() {
        int bound = 3;
        Square square = new Square();
        int[] result = square.calulate(bound);
        int[] expect = {1, 4, 9};
        assertThat(result, is(expect));
    }

    @Test
    /**
     * Тест: размер массива 5.
     */
    public void whenBound5Then1and4and9and16and25() {
        int bound = 5;
        Square square = new Square();
        int[] result = square.calulate(bound);
        int[] expect = {1, 4, 9, 16, 25};
        assertThat(result, is(expect));
    }

    @Test
    /**
     * Тест: размер массива 10.
     */
    public void whenBound10Then1and4and9and16and25and36and49and64and81and100() {
        int bound = 10;
        Square square = new Square();
        int[] result = square.calulate(bound);
        int[] expect = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
        assertThat(result, is(expect));
    }
}
