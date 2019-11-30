package ru.job4j.interactcalculator;

import org.junit.Test;
import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class InteractCalcTest {
    @Test
    public void consoleInputTest() {
        InputStream systemIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("2 + 2".getBytes()));
        System.setIn(in);

        InteractCalc calc = new InteractCalc();
        String result = calc.consoleInput("Input expression");
        System.setIn(systemIn);

        assertThat(result, is("2 + 2"));
    }

    @Test
    public void calculateSumTest() {
        InteractCalc calc = new InteractCalc();
        Double result = calc.calculate("2 + 2");

        assertThat(result, is(4D));
    }

    @Test
    public void calculateSubTest() {
        InteractCalc calc = new InteractCalc();
        Double result = calc.calculate("2 - 2");

        assertThat(result, is(0D));
    }

    @Test
    public void calculateMultTest() {
        InteractCalc calc = new InteractCalc();
        Double result = calc.calculate("2 * 2");

        assertThat(result, is(4D));
    }

    @Test
    public void calculateDivTest() {
        InteractCalc calc = new InteractCalc();
        Double result = calc.calculate("4 / 2");

        assertThat(result, is(2D));
    }

    @Test
    public void calculateWrongInputTest() {
        InteractCalc calc = new InteractCalc();
        Double result = calc.calculate("4 _ 2");

        assertNull(result);
    }

    @Test
    public void parserInputTest() {
        InteractCalc calc = new InteractCalc();
        boolean result = calc.parseUserInput("2 + 2");

        assertTrue(result);
    }

    @Test
    public void parserWrongMemoryInputTest() {
        InteractCalc calc = new InteractCalc();
        boolean result = calc.parseUserInput("2 + m");

        assertFalse(result);
    }

    @Test
    public void parserWrongCountOfBlocksInputTest() {
        InteractCalc calc = new InteractCalc();
        boolean result = calc.parseUserInput("2 +");

        assertFalse(result);
    }

    @Test
    public void parserWrongActionInputTest() {
        InteractCalc calc = new InteractCalc();
        boolean result = calc.parseUserInput("2 _ 0");

        assertFalse(result);
    }

    @Test
    public void parserWrongNumberInputTest() {
        InteractCalc calc = new InteractCalc();
        boolean result = calc.parseUserInput("2 * $");

        assertFalse(result);
    }
}