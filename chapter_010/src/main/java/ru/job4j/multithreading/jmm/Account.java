package ru.job4j.multithreading.jmm;

public class Account {
    /*volatile*/ private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    /*synchronized*/ public void withdraw(int sum) {
        int amount = this.balance;
        amount = amount - sum;
        this.balance = amount;
    }

    public void add(int sum) {
        int amount = this.balance;
        amount = amount + sum;
        this.balance = amount;
    }

    public int getBalance() {
        return balance;
    }
}