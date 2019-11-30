package ru.job4j.interactcalculator;

/**
 * Enumeration of calculator actions.
 */
public enum CalcAction {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String symbol;

    /**
     * Sets the character for action.
     * @param symbol Action symbol.
     */
    CalcAction(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the character of action.
     * @return Action symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets all symbols in a special format: [String symbol_1] [String symbol_2] ... [String symbol_n]
     * @return Special formatted string of all symbols.
     */
    public static String getFormattedSymbols() {
        StringBuilder result = new StringBuilder();
        for (CalcAction action : CalcAction.values()) {
            result.append(String.format("[%s]", action.getSymbol()));
        }
        return result.toString();
    }
}