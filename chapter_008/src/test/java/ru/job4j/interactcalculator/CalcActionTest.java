package ru.job4j.interactcalculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalcActionTest {

    @Test
    public void getFormattedSymbolsTest() {
        StringBuilder expect = new StringBuilder();
        for (CalcAction action : CalcAction.values()) {
            expect.append(String.format("[%s]", action.getSymbol()));
        }

        String result = CalcAction.getFormattedSymbols();

        assertThat(result, is(expect.toString()));
    }
}