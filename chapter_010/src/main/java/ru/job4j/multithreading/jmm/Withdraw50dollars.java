package ru.job4j.multithreading.jmm;

public class Withdraw50dollars implements Runnable {
    private Account account;

    public Withdraw50dollars(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        int balance = 0;
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(50);
                account.withdraw(1);
                ++balance;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%s balance = %d", Thread.currentThread().getName(), balance));
    }
}