package ru.job4j.bank;

import java.util.Objects;

/**
 * Банковский счет.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.08.2019
 * @version 1.0
 */
public class Account {
    private double value = 0;
    private int requisites;

    public Account() {
        this.requisites = setRequisites();
    }

    public Account(double value) {
        this.value = value;
        this.requisites = setRequisites();
    }

    private int setRequisites() {
        return (int) (Math.random() * 1999999999);
    }

    public double getValue() {
        return value;
    }

    public int getRequisites() {
        return requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites == account.requisites;
    }

    @Override
    public String toString() {
        return "Account{"
                + "value="
                + value
                + ", requisites="
                + requisites
                + '}';
    }
}