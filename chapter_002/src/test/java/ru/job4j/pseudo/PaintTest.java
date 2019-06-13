package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
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
 * @version 2.0
 */
public class PaintTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Заменяем стандартный вывод на вывод в память для тестирования.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Возвращаем обратно стандартный вывод в консоль.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     * Тест реализации квадрата в виде символов.
     *
     * PrintStream stdout = System.out - получаем ссылку на стандартный вывод в консоль.
     * ByteArrayOutputStream out = new ByteArrayOutputStream() - создаем буфер для хранения вывода.
     * new Paint().draw(new Square()) - выполняем действия пишущие в консоль.
     * assertThat... - проверяем результат вычисления.
     */
    @Test
    public void whenDrawSquare() {
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
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("     -      \n")
                                .append("   /   \\   \n")
                                .append("  /     \\  \n")
                                .append(" /       \\ \n")
                                .append(" --------- \n")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}

