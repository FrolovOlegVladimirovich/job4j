package ru.job4j.profession;

/**
 * Класс-наследник Доктора: Хирург.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 30.05.2019
 * @version 1.0
 */
public class Surgeon extends Doctor {
    private String operation;

    /**
     * Стандартный конструктор класса Surgeon.
     */
    public Surgeon() {
    }

    /**
     * @param operation Присваивает название операции.
     */
    public Surgeon(String operation) {
        this.operation = operation;
    }

    /**
     * Возвращает название операции.
     * @return operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Делает операцию
     */
    public void doASurgery() {
    }
}
