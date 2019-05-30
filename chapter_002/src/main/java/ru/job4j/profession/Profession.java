package ru.job4j.profession;

/**
 * Супер-класс: Профессия.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 30.05.2019
 * @version 1.0
 */
public class Profession {
    private String name;
    private String surename;
    private String birthday;
    private String education;
    private int experience;
    private double salary;

    /**
     * Стандартный конструктор класса Profession.
     */
    public Profession() {
    }

    /**
     * Присваивает значения:
     * @param name - имя.
     * @param surename - фамилия.
     * @param birthday - дата рождения.
     * @param education - образование.
     * @param experience - опыт работы.
     */
    public Profession(String name, String surename, String birthday, String education, int experience) {
        this.name = name;
        this.surename = surename;
        this.birthday = birthday;
        this.education = education;
        this.experience = experience;
    }

    /**
     * @param salary Присваивает значение размера зарплаты.
     */
    public Profession(double salary) {
        this.salary = salary;
    }

    /**
     * Возвращает имя.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает фамилию.
     * @return surename
     */
    public String getSurename() {
        return surename;
    }

    /**
     * Возвращает дату рождения.
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Возвращает образование.
     * @return education
     */
    public String getEducation() {
        return education;
    }

    /**
     * Возвращает опыт работы.
     * @return experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Возвращает размер зарплаты.
     * @return salary
     */
    public double getSalary() {
        return salary;
    }
}
