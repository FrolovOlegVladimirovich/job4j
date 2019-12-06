package ru.job4j.engineeringcalculator;

import ru.job4j.interactcalculator.CalcAction;
import ru.job4j.interactcalculator.DispatchCalc;

import java.util.function.Function;

/**
 * Dispatcher delegates the calculation based on the selected CalcAction.
 * Used pattern dispatcher.
 * Contains elements for engineering calculations.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class DispatchEngCalc extends DispatchCalc {
    protected EngCalc engCalc;

    /**
     * Default constructor.
     *
     * @param engCalc Calculator.
     * @param first      First number.
     * @param second     Second number.
     * @param action     Calculator action.
     */
    public DispatchEngCalc(EngCalc engCalc, Double first, Double second, CalcAction action) {
        this.engCalc = engCalc;
        super.calculator = engCalc;
        super.first = first;
        super.second = second;
        super.action = action;
    }

    @Override
    public DispatchCalc init() {
        super.init();
        this.load(CalcAction.SIN, this.toSin());
        this.load(CalcAction.COS, this.toCos());
        this.load(CalcAction.TG, this.toTg());
        return this;
    }

    /**
     * Handle to add action.
     * @return handle.
     */
    public Function<CalcAction, Double> toSin() {
        return action -> engCalc.sin(first);
    }

    /**
     * Handle to add action.
     * @return handle.
     */
    public Function<CalcAction, Double> toCos() {
        return action -> engCalc.cos(first);
    }

    /**
     * Handle to add action.
     * @return handle.
     */
    public Function<CalcAction, Double> toTg() {
        return action -> engCalc.tg(first);
    }
}