package ru.job4j.list;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест конвертации в двухмерного массива в ArrayList.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.08.2019
 * @version 1.0
 */
public class ConvertMatrix2ListTest {

    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4},
        };
        List<Integer> expect = List.of(1, 2, 3, 4);
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

    @Test
    public void when5And2ArrayThenList7() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2, 3, 4, 5},
                {6, 7},
        };
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

    @Test
    public void when5And2And3ArrayThenList10() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2, 3, 4, 5},
                {6, 7},
                {8, 9, 10}
        };
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }
}