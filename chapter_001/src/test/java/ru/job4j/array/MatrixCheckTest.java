package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixCheckTest {

    @Test
    public void whenDataMonoByTrueThenTrue() {
        boolean[][] matrix = {
                {true, true, true},
                {false, true, true},
                {true, false, true}
        };
        MatrixCheck check = new MatrixCheck();
        boolean result = check.mono(matrix);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        boolean[][] matrix = {
                {true, true, false},
                {false, false, true},
                {true, false, true}
        };
        MatrixCheck check = new MatrixCheck();
        boolean result = check.mono(matrix);
        assertThat(result, is(false));
    }

    @Test
    public void whenDataMonoByTrueThenTrue2() {
        boolean[][] matrix = {
                {true, false},
                {false, true}
        };
        MatrixCheck check = new MatrixCheck();
        boolean result = check.mono(matrix);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalse2() {
        boolean[][] matrix = {
                {true, false},
                {true, true}
        };
        MatrixCheck check = new MatrixCheck();
        boolean result = check.mono(matrix);
        assertThat(result, is(false));
    }
}