package ru.job4j.engineeringcalculator;

import org.junit.Test;

import ru.job4j.interactcalculator.CalcAction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Dispatcher engineering calculator tests.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class DispatchEngCalcTest {

    @Test
    public void dispatchSinActionTest() {
        EngCalc engCalc = new EngCalc();
        double number = 2.0;

        Double result = new DispatchEngCalc(engCalc, number, null, CalcAction.SIN).init().send();
        Double expect = Math.sin(number);

        assertThat(result, is(expect));
    }

    @Test
    public void dispatchCosActionTest() {
        EngCalc engCalc = new EngCalc();
        double number = 2.0;

        Double result = new DispatchEngCalc(engCalc, number, null, CalcAction.COS).init().send();
        Double expect = Math.cos(number);

        assertThat(result, is(expect));
    }

    @Test
    public void dispatchTgActionTest() {
        EngCalc engCalc = new EngCalc();
        double number = 2.0;

        Double result = new DispatchEngCalc(engCalc, number, null, CalcAction.TG).init().send();
        Double expect = Math.tan(number);

        assertThat(result, is(expect));
    }
}