package ru.job4j.profession;

/**
 * Класс-наследник Профессии: Учитель.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 30.05.2019
 * @version 1.0
 */
public class Teacher extends Profession {
    private int learners;
    private String subject;
    private int lessons;

    /**
     * Стандартный конструктор класса Teacher.
     */
    public Teacher() {
    }

    /**
     * @param learners Присваивает количество учеников.
     * @param lessons Присваивает количество уроков.
     */
    public Teacher(int learners, int lessons) {
        this.learners = learners;
        this.lessons = lessons;
    }

    /**
     * @param subject Присваивает название предмета.
     */
    public Teacher(String subject) {
        this.subject = subject;
    }

    /**
     * Возвращает количество учеников.
     * @return learners
     */
    public int getLearners() {
        return learners;
    }

    /**
     * Возвращает название предмета.
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Возвращает количество уроков.
     * @return lessons
     */
    public int getLessons() {
        return lessons;
    }

    /**
     * Обучает.
     */
    public void teachLearners() {

    }
}
