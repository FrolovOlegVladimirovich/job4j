package ru.job4j.engineeringcalculator;

import org.junit.Test;
import ru.job4j.interactcalculator.InteractCalc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Engineering calculator tests.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class EngCalcTest {
    @Test
    public void calculateSinTest() {
        InteractCalc calc = new EngCalc();
        Double result = calc.calculate("2 sin");

        assertThat(result, is(Math.sin(2.0)));
    }

    @Test
    public void calculateCosTest() {
        InteractCalc calc = new EngCalc();
        Double result = calc.calculate("2 cos");

        assertThat(result, is(Math.cos(2.0)));
    }

    @Test
    public void calculateTgTest() {
        InteractCalc calc = new EngCalc();
        Double result = calc.calculate("2 tg");

        assertThat(result, is(Math.tan(2.0)));
    }

    @Test
    public void engCalculateWrongInputTest() {
        InteractCalc calc = new EngCalc();
        Double result = calc.calculate("2");

        assertNull(result);
    }

    @Test
    public void engParserInputTest() {
        InteractCalc calc = new EngCalc();
        boolean result = calc.parseUserInput("2 cos");

        assertTrue(result);
    }

    @Test
    public void engParserWrongMemoryInputTest() {
        InteractCalc calc = new EngCalc();
        boolean result = calc.parseUserInput("m cos");

        assertFalse(result);
    }

    @Test
    public void engParserWrongCountOfBlocksInputTest() {
        InteractCalc calc = new EngCalc();
        boolean result = calc.parseUserInput("cos");

        assertFalse(result);
    }

    @Test
    public void engParserWrongCountOfBlocksInputTest2() {
        InteractCalc calc = new EngCalc();
        boolean result = calc.parseUserInput("34 2 cos 2");

        assertFalse(result);
    }

    @Test
    public void engParserWrongActionInputTest() {
        InteractCalc calc = new EngCalc();
        boolean result = calc.parseUserInput("2 coz");

        assertFalse(result);
    }

    @Test
    public void engParserWrongNumberInputTest() {
        InteractCalc calc = new InteractCalc();
        boolean result = calc.parseUserInput("f tg");

        assertFalse(result);
    }
}