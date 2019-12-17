package ru.job4j.parking;

import java.util.Objects;

/**
 * Passenger car.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class PassengerCar implements ICar {
    private final int id;
    private final int size;

    public PassengerCar(int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PassengerCar that = (PassengerCar) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}