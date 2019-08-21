package ru.job4j.stream;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест преобразования матрицы чисел в список чисел.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 21.08.2019
 * @version 1.0
 */
public class ConverterTest {
    @Test
    public void whenMatrixConvertToList() {
        List<List<Integer>> matrix = List.of(List.of(1, 2), List.of(3, 4), List.of(5, 6));
        List<Integer> result = new Converter().convertMatrixListToList(matrix);
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expect));
    }
}