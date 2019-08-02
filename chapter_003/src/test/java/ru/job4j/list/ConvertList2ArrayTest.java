package ru.job4j.list;

import org.junit.Test;
import java.util.Arrays;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест конвертации ArrayList в двухмерный массив.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.08.2019
 * @version 1.0
 */
public class ConvertList2ArrayTest {

    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when9ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when7ElementsThen8() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 2);
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen8() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), 2);
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when9ElementsThen10() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 2);
        int[][] expect = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when9ElementsThen10With5Rows() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 5);
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {9, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen10With5Rows() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), 5);
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {0, 0},
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen8With4Rows() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), 4);
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
        };
        assertThat(result, is(expect));
    }
}
