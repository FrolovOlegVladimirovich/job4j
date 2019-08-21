package ru.job4j.stream;

import java.util.Objects;

/**
 * Ученик с полем score - количество баллов.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class Student {
    private final int score;
    private final String firstName;
    private final String lastName;

    public Student(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{"
                + "score="
                + score
                + ", firstName='"
                + firstName + '\''
                + ", lastName='"
                + lastName + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score
                && firstName.equals(student.firstName)
                && lastName.equals(student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, firstName, lastName);
    }

    public int getScore() {
        return score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}