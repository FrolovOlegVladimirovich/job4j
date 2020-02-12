package ru.job4j.multithreading.tasks.elevatorsimulator;

/**
 * Elevator work thread.
 *
 * Elevator thread set as daemon in main constructor.
 * The program exits if the main thread terminated.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Elevator implements Runnable {
    private volatile int next = -1;
    private int current = 1;
    private int to = -1;
    private final int speed;
    private final long doorsTime;
    private final int floorHeight;
    private final int maxFloor;

    /**
     * Main constructor.
     *
     * @param speed - elevator moving speed in meters per second (ignore acceleration,
     *              consider that when the elevator goes, it immediately goes with a certain speed).
     * @param doorsTime - time between opening and closing doors (seconds).
     * @param floorHeight - floor height (meters).
     * @param maxFloor - maximum floor.
     */
    public Elevator(int speed, long doorsTime, int floorHeight, int maxFloor) {
        this.speed = speed;
        this.doorsTime = doorsTime;
        this.floorHeight = floorHeight;
        this.maxFloor = maxFloor;
    }

    /**
     * Sets next floor.
     * @param floor Next floor.
     */
    public void setFloor(int floor) {
        if (floor <= maxFloor &&  floor >= 1) {
            next = floor;
        }
    }

    /**
     * Shows elevator actions in real time, when there are actions in queue.
     *
     * @throws InterruptedException If thread is interrupted.
     */
    private void goesTo() throws InterruptedException {
        if (current != to) {
            boolean condition = true;
            do {
                System.out.println("Elevator passes " + current + " floor");
                if (to > current) {
                    current++;
                } else if (to < current) {
                    current--;
                } else {
                    condition = false;
                }
                Thread.sleep(floorHeight / speed * 1000);
            } while (condition);
            current = to;
            to = -1;
            System.out.println("Elevator arrived on the " + current + " floor");
            Thread.sleep(1500);
        }
        System.out.println("Elevator opened the doors");
        Thread.sleep(doorsTime);
        System.out.println("Elevator closed the doors");
    }

    /**
     * The thread waits for next floor.
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                if (next != -1) {
                    to = next;
                    next = -1;
                    goesTo();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}