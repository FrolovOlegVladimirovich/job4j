package ru.job4j.multithreading.tasks.elevatorsimulator;

import java.io.InputStream;

/**
 * Start class to run the elevator simulator application.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Start {
    private final int maxFloor;
    private final int height;
    private final int speed;
    private final long doorsTime;
    private final InputStream userInput;

    /**
     * Main constructor.
     * Validate args.
     *
     * @param args - program arguments from main().
     * @param userInput - user inputStream.
     * @throws IllegalArgumentException if wrong program arguments (see main() docs).
     */
    public Start(String[] args, InputStream userInput) throws IllegalArgumentException {
        if (args.length != 8) {
            throw new IllegalArgumentException();
        }
        int maxFloor = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[3]);
        int speed = Integer.parseInt(args[5]);
        long doorsTime = Integer.parseInt(args[7]) * 1000;
        if (!"-f".equals(args[0])
                || !"-h".equals(args[2])
                || !"-s".equals(args[4])
                || !"-d".equals(args[6])
                || (maxFloor < 5 || maxFloor > 20)
                || (height < 2 || height > 10)
                || (speed < 1 || speed > 10)
                || (doorsTime > 10000 || doorsTime < 1000)
        ) {
            throw new IllegalArgumentException();
        }
        this.maxFloor = maxFloor;
        this.height = height;
        this.speed = speed;
        this.doorsTime = doorsTime;
        this.userInput = userInput;
    }

    /**
     * Init all threads: elevator & control.
     *
     * Elevator thread set as daemon.
     * The program exits if the main thread terminated.
     *
     * @throws InterruptedException If thread is interrupted.
     */
    public void init() throws InterruptedException, IllegalArgumentException {
        Elevator elevator = new Elevator(speed, doorsTime, height, maxFloor);
        ControlPanel control = new ControlPanel(elevator, userInput);
        Thread elevatorTh = new Thread(elevator);
        elevatorTh.setDaemon(true);
        elevatorTh.setName("Elevator");
        Thread controlTh = new Thread(control);
        controlTh.setName("Control");
        elevatorTh.start();
        controlTh.start();
        controlTh.join();
    }

    /**
     * Main thread.
     *
     * @param args:
     * [-f] [number_of_floors == 5-20]
     * [-h] [floor_height == 2-10]
     * [-s] [elevator_speed == 1-10]
     * [-d] [time_between_opening_and_closing_doors == 1-10]
     *
     * @throws InterruptedException If thread is interrupted.
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            new Start(args, System.in).init();
        } catch (IllegalArgumentException ex) {
            System.out.println(
                    new StringBuilder("Invalid arguments! ")
                            .append("Should be:")
                            .append(System.lineSeparator())
                            .append("[-f] [number_of_floors == 5-20]")
                            .append(System.lineSeparator())
                            .append("[-h] [floor_height == 2-10] ")
                            .append(System.lineSeparator())
                            .append("[-s] [elevator_speed == 1-10]")
                            .append(System.lineSeparator())
                            .append("[-d] [time_between_opening_and_closing_doors == 1-10]")
                            .append(System.lineSeparator())
            );
        }
    }
}