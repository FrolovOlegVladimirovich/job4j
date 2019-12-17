package ru.job4j.parking;

/**
 * Parking contains spaces for cars and trucks.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class CarAndTruckParking implements IParking {
    private final IParking carParking;
    private final IParking truckParking;

    public CarAndTruckParking(IParking carParking, IParking truckParking) {
        this.carParking = carParking;
        this.truckParking = truckParking;
    }

    /**
     * Parks the car.
     * @param car Car.
     * @return true if the car is successfully parked.
     */
    @Override
    public boolean startParking(ICar car) {
        return truckParking.startParking(car) || carParking.startParking(car);
    }

    /**
     * Stops the car parking.
     * @param car Car.
     * @return true if the car is successfully finished parking.
     */
    @Override
    public boolean finishParking(ICar car) {
        return truckParking.finishParking(car) || carParking.finishParking(car);
    }

    /**
     * Counts the number of free parking spaces.
     * @return the number of free parking spaces.
     */
    @Override
    public int countFreeSpace() {
        return carParking.countFreeSpace() + truckParking.countFreeSpace();
    }

    /**
     * Checks free parking spaces.
     * @return true if there is at least one free parking space.
     */
    @Override
    public boolean checkFreeSpace() {
        return carParking.checkFreeSpace() || truckParking.checkFreeSpace();
    }
}