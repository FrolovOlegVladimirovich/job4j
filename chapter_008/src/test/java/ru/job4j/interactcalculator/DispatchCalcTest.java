package ru.job4j.interactcalculator;

import org.junit.Test;
import ru.job4j.calculator.Calculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DispatchCalcTest {
    @Test
    public void dispatchAddActionTest() {
        Calculator calculator = new Calculator();
        Double result = new DispatchCalc(calculator, 2.5, 1.5, CalcAction.ADD).init().send();

        assertThat(result, is(4.0));
    }

    @Test
    public void dispatchSubtractActionTest() {
        Calculator calculator = new Calculator();
        Double result = new DispatchCalc(calculator, 2.5, 1.5, CalcAction.SUBTRACT).init().send();

        assertThat(result, is(1.0));
    }

    @Test
    public void dispatchMultiplyActionTest() {
        Calculator calculator = new Calculator();
        Double result = new DispatchCalc(calculator, 6.0, 2.0, CalcAction.MULTIPLY).init().send();

        assertThat(result, is(12.0));
    }

    @Test
    public void dispatchDivideActionTest() {
        Calculator calculator = new Calculator();
        Double result = new DispatchCalc(calculator, 6.0, 2.0, CalcAction.DIVIDE).init().send();

        assertThat(result, is(3.0));
    }
}