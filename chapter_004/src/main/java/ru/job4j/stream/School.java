package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
}