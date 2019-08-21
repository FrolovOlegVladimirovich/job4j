package ru.job4j.stream;

import org.junit.Test;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест методов для работы со списком учеников.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class SchoolTest {
    final List<Student> students = List.of(
            new Student("Еремей", "Евтушенков", 40),
            new Student("Мирослав", "Григорьев", 10),
            new Student("Ульяна", "Курпатова", 24),
            new Student("Ким", "Ельцов", 90),
            new Student("Евдокия", "Фукина", 85),
            new Student("Касьян", "Чечёткин", 43),
            new Student("Игорь", "Нустров", 27),
            new Student("Федот", "Коржев", 75),
            new Student("Кир", "Ярыкин", 84),
            new Student("Эвелина", "Заседателева", 51),
            new Student("Розалия", "Пономарева", 67),
            new Student("Потап", "Андрюхин", 19),
            new Student("Осип", "Сушилов", 29),
            new Student("Аза", "Брязгина", 97),
            new Student("Марина", "Кудрявцева", 100),
            new Student("Лев", "Бехтерев", 8)
    );
    final School school = new School();

    /**
     * Сортировка учеников в диапазоне от 0 до 50 баллов.
     */
    @Test
    public void filterFrom0To50() {
        List<Student> result = school.collect(students, student -> student.getScore() <= 50);
        List<Student> list = List.of(
                new Student("Еремей", "Евтушенков", 40),
                new Student("Мирослав", "Григорьев", 10),
                new Student("Ульяна", "Курпатова", 24),
                new Student("Касьян", "Чечёткин", 43),
                new Student("Игорь", "Нустров", 27),
                new Student("Потап", "Андрюхин", 19),
                new Student("Осип", "Сушилов", 29),
                new Student("Лев", "Бехтерев", 8));
        assertThat(result, is(list));
    }

    /**
     * Сортировка учеников в диапазоне от 50 до 70 баллов.
     */
    @Test
    public void filterFrom50To70() {
        List<Student> result = school.collect(students, student -> student.getScore() > 50 && student.getScore() <= 70);
        List<Student> list = List.of(
                new Student("Эвелина", "Заседателева", 51),
                new Student("Розалия", "Пономарева", 67));
        assertThat(result, is(list));
    }

    /**
     * Сортировка учеников в диапазоне от 70 до 100 баллов.
     */
    @Test
    public void filterFrom70To100() {
        List<Student> result = school.collect(students, student -> student.getScore() > 70 && student.getScore() <= 100);
        List<Student> list = List.of(
                new Student("Ким", "Ельцов", 90),
                new Student("Евдокия", "Фукина", 85),
                new Student("Федот", "Коржев", 75),
                new Student("Кир", "Ярыкин", 84),
                new Student("Аза", "Брязгина", 97),
                new Student("Марина", "Кудрявцева", 100));
        assertThat(result, is(list));
    }

    /**
     * Преобразование списка учеников из List в Map.
     */
    @Test
    public void convertStudentsFromListToMap() {
        Student evtushenkov = new Student("Еремей", "Евтушенков", 40);
        Student grigorev = new Student("Мирослав", "Григорьев", 10);
        Student kurpatova = new Student("Ульяна", "Курпатова", 24);
        List<Student> students = List.of(evtushenkov, grigorev, kurpatova);
        Map<String, Student> result = school.collectStudentsFromListToMap(students);
        Map<String, Student> expect = new TreeMap<>();
        expect.put("Евтушенков", evtushenkov);
        expect.put("Григорьев", grigorev);
        expect.put("Курпатова", kurpatova);
        assertThat(result, is(expect));
    }
}