package ru.job4j.profession;

/**
 * Класс-наследник Инженер: Программист.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 30.05.2019
 * @version 1.0
 */
public class Programmer extends Engineer {
    private String language;

    /**
     * @param language Присваивает название языка программирования.
     */
    public Programmer(String language) {
        this.language = language;
    }

    /**
     * Возвращает название языка программирования.
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Пишет код.
     */
    public void writeCode() {

    }

    /**
     * Правит ошибки.
     */
    public void fixBugs() {

    }
}
