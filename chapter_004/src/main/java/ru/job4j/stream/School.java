package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Методы для работы со списком учеников.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class School {
    /**
     * Фильтрация учеников в зависимости от заданного диапазона баллов.
     * @param students Список учеников.
     * @param predict Условие фильтрации учеников.
     * @return Отфильтрованный список учеников.
     */
    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    /**
     * Преобразование списка учеников из List в Map.
     * @param students Список учеников.
     * @return Map учеников (ключ: фамилия ученика; значение: объект ученика).
     */
    Map<String, Student> collectStudentsFromListToMap(List<Student> students) {
        return students.stream().collect(Collectors.toMap(Student::getLastName, student -> student));
    }

    /**
     * Фильтр учеников по количеству баллов больше bound.
     * @param students Список учеников.
     * @param bound Колличество баллов.
     * @return List учеников с количеством баллов больше bound.
     */
    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted(Comparator.nullsFirst((o1, o2) -> o2.getScore() - o1.getScore()))
                .flatMap(Stream::ofNullable)
                .takeWhile(score -> score.getScore() > bound)
                .collect(Collectors.toList());
    }
}