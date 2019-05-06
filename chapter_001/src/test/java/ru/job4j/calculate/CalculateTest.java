package ru.job4j.calculate;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @version 1.0
 * @since 05.05.2019
 */
public class CalculateTest {
    /**
     * Test add
     */
    @Test
    public void whenAddOneToOneThenTwo() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculate.main(null);
        assertThat(out.toString(), is("Hello world!\n"));
    }
    /**
     * Test echo
     */
    @Test
    public void whenTakeNameThenThreeEchoPlusName() {
        String input = "Oleg Frolov";
        String expect = "Echo, echo, echo : Oleg Frolov";
        Calculate calc = new Calculate();
        String result = calc.echo(input);
        assertThat(result, is(expect));
    }
}
