package ru.job4j.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Фильтрация учеников в зависимости от заданного диапазона баллов.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class School {
    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }
}