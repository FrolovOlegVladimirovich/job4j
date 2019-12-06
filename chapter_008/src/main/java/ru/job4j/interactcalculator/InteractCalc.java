package ru.job4j.interactcalculator;
import ru.job4j.calculator.Calculator;
import ru.job4j.engineeringcalculator.EngCalc;

import java.util.*;

/**
 * Interactive calculator with console input/output and memory storage.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class InteractCalc extends Calculator {
    private final String exit = "q";
    protected Double first;
    protected Double second;
    protected Double memory;
    protected Double result;
    protected CalcAction action;
    protected DispatchCalc dispatchCalc;

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
            createDispatcher();
            result = dispatchCalc.init().send();
        }
        first = null;
        second = null;
        action = null;
        return result;
    }

    protected void createDispatcher() {
        dispatchCalc = new DispatchCalc(this, first, second, action);
    }

    /**
     * Parses user input on values and operation symbol.
     * @param input User input.
     * @return True if parsing was successful.
     */
    public boolean parseUserInput(String input) {
        String action = checkAction(input);
        if (action == null) {
            return false;
        }
        String[] elements = checkNumOfDigits(input);
        if (elements == null) {
            return false;
        }
        List<Double> numbers = checkNumbers(elements);
        if (numbers == null) {
            return false;
        }
        Object[] numArray = numbers.toArray();
        if (numbers.isEmpty()) {
            return false;
        } else if (numbers.size() == 2) {
            this.first = (Double) numArray[0];
            this.second = (Double) numArray[1];
        } else {
            this.first = numbers.get(0);
        }
        return true;
    }

    /**
     * Checks input for a valid action character.
     * @param input User input.
     * @return Returns the character if validation is successful.
     */
    protected String checkAction(String input) {
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
            if (!exit.equals(input)) {

                System.out.println(String.format(
                        "Missing valid operation character. Should be %s",
                        CalcAction.getFormattedSymbols())
                );
            }
        }
        return action;
    }

    /**
     * Checks the number of digits in the input.
     * @param input User input.
     * @return Array of numbers.
     */
    protected String[] checkNumOfDigits(String input) {
        String[] elements = input.split(String.format("\\%s", action.getSymbol()));
        return elements.length != 2 ? null : elements;
    }

    /**
     * Checks if characters are numbers.
     * @param elements Elements that should contain numbers.
     * @return List of numbers.
     */
    protected List<Double> checkNumbers(String[] elements) {
        List<Double> numbers = new ArrayList<>();
        for (String element : elements) {
            try {
                numbers.add(Double.valueOf(element.trim()));
            } catch (NumberFormatException e) {
                if ("m".equals(element.trim())) {
                    if (memory != null) {
                        numbers.add(memory);
                    } else {
                        System.out.println("No value in memory.");
                        return null;
                    }
                } else {
                    System.out.println("Invalid character instead of number.");
                    return null;
                }
            }
        }
        return numbers;
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
                "Expression must contain three elements for simple operations: [number1]",
                "[operation_symbol] [number2]",
                "Engineering calculator: [number] [operation_symbol]",
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
        EngCalc calc = new EngCalc();
        calc.init();
    }
}