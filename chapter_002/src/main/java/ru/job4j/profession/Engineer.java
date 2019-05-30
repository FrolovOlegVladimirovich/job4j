package ru.job4j.profession;

/**
 * Класс-наследник Профессии: Инженер.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 30.05.2019
 * @version 1.0
 */
public class Engineer extends Profession {
    private int projects;

    /**
     * Стандартный конструктор класса Engineer.
     */
    public Engineer() {

    }

    /**
     * @param projects Приваивает количество проектов.
     */
    public Engineer(int projects) {
        this.projects = projects;
    }

    /**
     * Возвращает количество проектов.
     * @return projects
     */
    public int getProjects() {
        return projects;
    }
}
