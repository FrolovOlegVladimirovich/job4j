package ru.job4j.pseudo;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Тест вывода фигуры в консоль.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.06.2019
 * @version 1.0
 */
public class PaintTest {

    /**
     * Тест реализации квадрата в виде символов.
     *
     * PrintStream stdout = System.out - получаем ссылку на стандартный вывод в консоль.
     * ByteArrayOutputStream out = new ByteArrayOutputStream() - создаем буфер для хранения вывода.
     * System.setOut(new PrintStream(out)) - заменяем стандартный вывод на вывод в память для тестирования.
     * new Paint().draw(new Square()) - выполняем действия пишущие в консоль.
     * assertThat... - проверяем результат вычисления.
     * System.setOut(stdout) - возвращаем обратно стандартный вывод в консоль.
     */
    @Test
    public void whenDrawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(" ----------\n")
                                .append("|          |\n")
                                .append("|          |\n")
                                .append("|          |\n")
                                .append(" ----------\n")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        System.setOut(stdout);
    }
}
