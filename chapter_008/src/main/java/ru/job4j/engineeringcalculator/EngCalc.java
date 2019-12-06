package ru.job4j.engineeringcalculator;

import ru.job4j.interactcalculator.CalcAction;
import ru.job4j.interactcalculator.InteractCalc;

/**
 * Engineering calculator.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class EngCalc extends InteractCalc {

    @Override
    protected void createDispatcher() {
        dispatchCalc = new DispatchEngCalc(this, first, second, action);
    }

    @Override
    protected String[] checkNumOfDigits(String input) {
        String[] result = super.checkNumOfDigits(input);
        String symbol = action.getSymbol();
        input = input.replace(symbol, "");
        if (result == null && CalcAction.getEngSymbols().contains(action) && !"".equals(input)) {
            result = new String[] {input.trim()};
        } else {
            System.out.println("Invalid number of elements in expression.");
        }
        return result;
    }

    /**
     * Calculates COS.
     * @param number Number to calculate.
     * @return Calculation result.
     */
    public double cos(double number) {
        return Math.cos(number);
    }

    /**
     * Calculates SIN.
     * @param number Number to calculate.
     * @return Calculation result.
     */
    public double sin(double number) {
        return Math.sin(number);
    }

    /**
     * Calculates TG.
     * @param number Number to calculate.
     * @return Calculation result.
     */
    public double tg(double number) {
        return Math.tan(number);
    }
}