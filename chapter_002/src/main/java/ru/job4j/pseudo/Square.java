package ru.job4j.pseudo;

/**
 * Реализует квадрат в виде символов.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.06.2019
 * @version 1.0
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append(" ----------\n");
        pic.append("|          |\n");
        pic.append("|          |\n");
        pic.append("|          |\n");
        pic.append(" ----------\n");
        return pic.toString();
    }
}
