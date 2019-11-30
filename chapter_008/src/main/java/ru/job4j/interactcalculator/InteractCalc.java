package ru.job4j.interactcalculator;
import ru.job4j.calculator.Calculator;

import java.util.Scanner;

/**
 * Interactive calculator with console input/output and memory storage.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class InteractCalc extends Calculator {
    private final String exit = "q";
    private Double first;
    private Double second;
    private Double memory;
    private Double result;
    private CalcAction action;

    /**
     * Console input.
     * @param request User request.
     * @return User input result.
     */
    public String consoleInput(String request) {
        System.out.println(request);
        Scanner scanner = new Scanner(System.in);
        String result = null;
        if (scanner.hasNext()) {
            result = scanner.nextLine();
        }
        return result;
    }

    /**
     * Calculate numbers.
     * @param input User input.
     * @return Calculation result.
     */
    public Double calculate(String input) {
        Double result = null;
        if (parseUserInput(input)) {
            result = new DispatchCalc(this, first, second, action).init().send();
        }
        first = null;
        second = null;
        action = null;
        return result;
    }

    /**
     * Parses user input on values and operation symbol.
     * @param input User input.
     * @return True if parsing was successful.
     */
    public boolean parseUserInput(String input) {
        String action = null;
        for (CalcAction value : CalcAction.values()) {
            String symbol = value.getSymbol();
            if (input.contains(symbol)) {
                action = symbol;
                this.action = value;
                break;
            }
        }
        if (action == null) {
            if (exit.equals(input)) {
                return false;
            }
            System.out.println(String.format(
                    "Missing valid operation character. Should be %s",
                    CalcAction.getFormattedSymbols())
            );
            return false;
        }
        String[] elements = input.split(String.format("\\%s", action));
        if (elements.length != 2) {
            System.out.println(String.format(
                    "Expression must contain three elements: [number1] %s [number2]",
                    CalcAction.getFormattedSymbols()));
            return false;
        }
        Double[] numbers = new Double[2];
        for (int i = 0; i < elements.length; i++) {
            try {
                numbers[i] = Double.valueOf(elements[i].trim());
            } catch (NumberFormatException e) {
                if ("m".equals(elements[i].trim())) {
                    if (memory != null) {
                        numbers[i] = memory;
                    } else {
                        System.out.println("No value in memory.");
                        return false;
                    }
                } else {
                    System.out.println("Invalid character instead of number.");
                    return false;
                }
            }
        }
        this.first = numbers[0];
        this.second = numbers[1];
        return true;
    }

    /**
     * Stores value in memory.
     */
    private void saveToMemory() {
        String input = consoleInput(String.format("%s\n%s", "Save result to memory?",
                "Enter [y] to save the value in memory, or any character to cancel."));
        if ("y".equals(input)) {
            memory = result;
            System.out.println(String.format("%s %f %s\n%s", "Value", memory, "is saved to the memory.",
                    "To get a value from memory, use the [m] character"));
        } else {
            System.out.println("Saving Canceled.");
        }
    }

    /**
     * Init main loop.
     */
    public void init() {
        System.out.printf("%s\n%s %s %s\n%s\n%s\n",
                "-------Interactive calculator-------",
                "Expression must contain three elements: [number1]",
                CalcAction.getFormattedSymbols(),
                "[number2]",
                "To get a value from memory, use the [m] character",
                "Enter [q] to exit.");
        String input;
        do {
            input = consoleInput("Enter expression");
            result = calculate(input);
            if (result != null) {
                System.out.println("= " + result);
                saveToMemory();
            }
        } while (!exit.equals(input));
    }

    /**
     * Starts program.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc();
        calc.init();
    }
}