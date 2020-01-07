package ru.job4j.multithreading.jmm;

/**
 * This example illustrates the problem of race conditions when using multithreading.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class RaceConditionsExample {
    /**
     * Thread user1 50 times withdraws $1 from the account.
     * The total amount withdrawn by the user1 from the account will be $50.
     * Thread user2 performs a similar operation with the same account.
     *
     * As a result of operations of user1 and user2, the balance should be $0.
     * In fact the result is unpredictable.
     *
     * To resolve this problem withdraw method in the Account class should be synchronized.
     */
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(100);
        System.out.println(String.format("Start account balance %d", account.getBalance()));
        Thread user1 = new Thread(new Withdraw50dollars(account), "User1");
        Thread user2 = new Thread(new Withdraw50dollars(account), "User2");
        user1.start();
        user2.start();
        user1.join();
        user2.join();
        System.out.println(String.format("Result account balance %d", account.getBalance()));
    }
}