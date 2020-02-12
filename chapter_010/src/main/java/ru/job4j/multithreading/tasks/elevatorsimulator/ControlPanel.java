package ru.job4j.multithreading.tasks.elevatorsimulator;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Thread to call the elevator.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class ControlPanel implements Runnable {
    private final Scanner scanner;
    private final Elevator elevator;

    /**
     * Main constructor.
     *
     * @param elevator - Controlled elevator.
     * @param userInput - user InputStream.
     */
    public ControlPanel(Elevator elevator, InputStream userInput) {
        this.elevator = elevator;
        this.scanner = new Scanner(userInput);
    }

    /**
     * Thread continuously waits for user input until it's interrupted.
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.matches("^\\d+$")) {
                    elevator.setFloor(Integer.parseInt(input));
                } else if ("q".equals(input)) {
                    Thread.currentThread().interrupt();
                } else {
                    System.out.println("Invalid input. Try again." + System.lineSeparator());
                }
            }
        }
    }
}