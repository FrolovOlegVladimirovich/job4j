package ru.job4j.interactcalculator;

import ru.job4j.calculator.Calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Dispatcher delegates the calculation based on the selected CalcAction.
 * Used pattern dispatcher.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class DispatchCalc {
    private final Calculator calculator;
    private final Double first;
    private final Double second;
    private final CalcAction action;
    private final Map<CalcAction, Function<CalcAction, Double>> dispatch = new HashMap<>();

    /**
     * Default constructor.
     * @param calculator Calculator.
     * @param first First number.
     * @param second Second number.
     * @param action Calculator action.
     */
    public DispatchCalc(Calculator calculator, Double first, Double second, CalcAction action) {
        this.calculator = calculator;
        this.first = first;
        this.second = second;
        this.action = action;
    }

    /**
     * Initializes dispatch.
     * @return current object.
     */
    public DispatchCalc init() {
        this.load(CalcAction.ADD, this.toAdd());
        this.load(CalcAction.SUBTRACT, this.toSubtract());
        this.load(CalcAction.MULTIPLY, this.toMultiply());
        this.load(CalcAction.DIVIDE, this.toDivide());
        return this;
    }

    /**
     * Loads handlers for destinations.
     * @param action Calculator action.
     * @param handle Handler.
     */
    public void load(CalcAction action, Function<CalcAction, Double> handle) {
        this.dispatch.put(action, handle);
    }

    /**
     * Send CalcAction to dispatch.
     * @return Calculation result.
     */
    public Double send() {
        return this.dispatch.get(this.action).apply(this.action);
    }

    /**
     * Handle to add action.
     * @return handle.
     */
    public Function<CalcAction, Double> toAdd() {
        return action -> calculator.add(first, second);
    }

    /**
     * Handle to subtract action.
     * @return handle.
     */
    public Function<CalcAction, Double> toSubtract() {
        return action -> calculator.subtract(first, second);
    }

    /**
     * Handle to multiply action.
     * @return handle.
     */
    public Function<CalcAction, Double> toMultiply() {
        return action -> calculator.multiple(first, second);
    }

    /**
     * Handle to divide action.
     * @return handle.
     */
    public Function<CalcAction, Double> toDivide() {
        return action -> calculator.div(first, second);
    }
}