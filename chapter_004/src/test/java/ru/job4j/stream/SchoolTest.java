package ru.job4j.stream;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест фильтрации учеников в зависимости от заданного диапазона баллов.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class SchoolTest {
    final List<Student> students = List.of(
            new Student(40),
            new Student(10),
            new Student(24),
            new Student(90),
            new Student(85),
            new Student(43),
            new Student(27),
            new Student(75),
            new Student(84),
            new Student(51),
            new Student(67),
            new Student(19),
            new Student(29),
            new Student(97),
            new Student(100),
            new Student(8)
    );
    final School school = new School();

    /**
     * Сортировка учеников в диапазоне от 0 до 50 баллов.
     */
    @Test
    public void filterFrom0To50() {
        List<Student> result = school.collect(students, student -> student.getScore() <= 50);
        List<Student> list = List.of(
                new Student(40),
                new Student(10),
                new Student(24),
                new Student(43),
                new Student(27),
                new Student(19),
                new Student(29),
                new Student(8));
        assertThat(result, is(list));
    }

    /**
     * Сортировка учеников в диапазоне от 50 до 70 баллов.
     */
    @Test
    public void filterFrom50To70() {
        List<Student> result = school.collect(students, student -> student.getScore() > 50 && student.getScore() <= 70);
        List<Student> list = List.of(
                new Student(51),
                new Student(67));
        assertThat(result, is(list));
    }

    /**
     * Сортировка учеников в диапазоне от 70 до 100 баллов.
     */
    @Test
    public void filterFrom70To100() {
        List<Student> result = school.collect(students, student -> student.getScore() > 70 && student.getScore() <= 100);
        List<Student> list = List.of(
                new Student(90),
                new Student(85),
                new Student(75),
                new Student(84),
                new Student(97),
                new Student(100));
        assertThat(result, is(list));
    }
}