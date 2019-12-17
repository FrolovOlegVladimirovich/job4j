package ru.job4j.parking;

import java.util.HashSet;
import java.util.Set;

/**
 * Truck parking can take only trucks.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class TruckParking implements IParking {
    private final int totalSpace;
    private final int spaceSize;
    private final Set<ICar> parkedCars;
    private int freeSpace;
    private int occupiedSpace;


    public TruckParking(int totalSpace, int spaceSize) {
        this.totalSpace = totalSpace;
        this.spaceSize = spaceSize;
        this.freeSpace = totalSpace;
        this.parkedCars = new HashSet<>();
    }

    /**
     * Parks only truck cars.
     * @param car Car.
     * @return true if the car is successfully parked.
     */
    @Override
    public boolean startParking(ICar car) {
        if (car.getSize() == spaceSize && freeSpace > 0) {
            parkedCars.add(car);
            freeSpace--;
            occupiedSpace++;
            return true;
        }
        return false;
    }

    /**
     * Stops the car parking.
     * @param car Car.
     * @return true if the car is successfully finished parking.
     */
    @Override
    public boolean finishParking(ICar car) {
        if (parkedCars.remove(car)) {
            freeSpace++;
            occupiedSpace--;
            return true;
        }
        return false;
    }

    /**
     * Counts the number of free parking spaces.
     * @return the number of free parking spaces.
     */
    @Override
    public int countFreeSpace() {
        return freeSpace;
    }

    /**
     * Checks free parking spaces.
     * @return true if there is at least one free parking space.
     */
    @Override
    public boolean checkFreeSpace() {
        return freeSpace > 0;
    }
}