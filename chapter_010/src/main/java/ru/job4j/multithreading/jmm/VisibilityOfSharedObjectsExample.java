package ru.job4j.multithreading.jmm;

/**
 * This example illustrates the problem of shared objects visibility when using multithreading.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class VisibilityOfSharedObjectsExample {
    /**
     * Thread user1 deposits money into the account while thread user2 expects money to the account.
     * When user1 has completed the deposit operation and the account balance != 0,
     * user2 continues to wait for money in the account.
     *
     * To resolve this problem in the account instance variable balance by making volatile.
     */
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);
        Thread user1 = new Thread(()-> {
            int balance = 100;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.add(balance);
            balance = 0;
            System.out.println(String.format("User1 balance = %d", balance));
        });
        Thread user2 = new Thread(() -> {
            while (account.getBalance() == 0) {
                int i = 0; // some code inside loop
            }
            System.out.println(String.format("$ %d received in the User's2 account", account.getBalance()));
        });
        user1.start();
        user2.start();
        user1.join();
        user2.join();
        System.out.println(String.format("Account balance %d", account.getBalance()));
    }
}