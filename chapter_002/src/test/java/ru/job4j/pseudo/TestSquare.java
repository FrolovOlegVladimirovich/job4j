package ru.job4j.pseudo;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест реализации квадрата в виде символов.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.06.2019
 * @version 1.0
 */
public class TestSquare {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(square.draw(), is(
                new StringBuilder()
                        .append(" ----------\n")
                        .append("|          |\n")
                        .append("|          |\n")
                        .append("|          |\n")
                        .append(" ----------\n")
                        .toString()
                )
        );
    }
}
