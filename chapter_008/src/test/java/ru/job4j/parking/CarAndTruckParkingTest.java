package ru.job4j.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarAndTruckParkingTest {

    @Test
    public void whenParkingCarResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(10, 2)
        );
        ICar car = new PassengerCar(1, 1);

        boolean result = parking.startParking(car);

        assertTrue(result);
    }

    @Test
    public void whenParkingCarIsOccupiedResultIsFalse() {
        IParking parking = new CarAndTruckParking(
                new CarParking(1, 1),
                new TruckParking(10, 2)
        );
        ICar car1 = new PassengerCar(1, 1);
        ICar car2 = new PassengerCar(2, 1);
        parking.startParking(car1);

        boolean result = parking.startParking(car2);

        assertFalse(result);
    }

    @Test
    public void whenFinishParkingCarResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(1, 1),
                new TruckParking(10, 2)
        );
        ICar car1 = new PassengerCar(1, 1);
        parking.startParking(car1);

        boolean result = parking.finishParking(car1);

        assertTrue(result);
    }

    @Test
    public void whenFinishParkingCarResultIsFalse() {
        IParking parking = new CarAndTruckParking(
                new CarParking(1, 1),
                new TruckParking(10, 5)
        );
        ICar car1 = new PassengerCar(1, 1);
        ICar car2 = new PassengerCar(2, 1);

        parking.startParking(car1);
        parking.startParking(car2);

        boolean result = parking.finishParking(car2);

        assertFalse(result);
    }

    @Test
    public void whenParkingSizeIs5And2SpacesOccupiedCountFreeSpaceResultIs8() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(5, 5)
        );
        ICar car1 = new PassengerCar(1, 1);
        ICar car2 = new PassengerCar(2, 1);
        parking.startParking(car1);
        parking.startParking(car2);

        int result = parking.countFreeSpace();

        assertThat(result, is(8));
    }

    @Test
    public void whenParkingSizeIs15And2SpacesOccupiedFreeSpaceResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(10, 2)
        );
        ICar car1 = new PassengerCar(1, 1);
        ICar car2 = new PassengerCar(2, 1);
        parking.startParking(car1);
        parking.startParking(car2);

        boolean result = parking.checkFreeSpace();

        assertTrue(result);
    }

    @Test
    public void whenParkingSizeIs2And2SpacesOccupiedFreeSpaceResultIsFalse() {
        IParking parking = new CarAndTruckParking(
                new CarParking(2, 1),
                new TruckParking(0, 2)
        );
        ICar car1 = new PassengerCar(1, 1);
        ICar car2 = new PassengerCar(2, 1);
        parking.startParking(car1);
        parking.startParking(car2);

        boolean result = parking.checkFreeSpace();

        assertFalse(result);
    }

    @Test
    public void whenParkingTruckOnFreeTruckSpaceResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(10, 5)
        );
        ICar truck = new TruckCar(1, 5);

        boolean result = parking.startParking(truck);

        assertTrue(result);
    }

    @Test
    public void whenParkingTruckIfTruckParkingIsOccupiedAndCarParkingIsFreeResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(10, 5)
        );
        ICar truck1 = new TruckCar(2, 5);
        ICar truck2 = new TruckCar(3, 5);
        ICar truck3 = new TruckCar(4, 5);
        parking.startParking(truck1);
        parking.startParking(truck2);

        boolean result = parking.startParking(truck3);

        assertTrue(result);
    }

    @Test
    public void whenParkingTruckIfPartOfCarParkingIsFreeAndTruckParkingIsOccupiedResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(10, 1),
                new TruckParking(1, 5)
        );
        ICar car1 = new PassengerCar(1, 1);
        ICar car2 = new PassengerCar(2, 1);
        ICar truck1 = new TruckCar(3, 5);
        ICar truck2 = new TruckCar(4, 5);
        parking.startParking(car1);
        parking.startParking(car2);
        parking.startParking(truck1);

        boolean result = parking.startParking(truck2);

        assertTrue(result);
    }

    @Test
    public void whenParkingTruckIfPartOfCarParkingIsFreeButNotEnoughSpaceAndTruckParkingIsOccupiedResultIsFalse() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(1, 5)
        );
        ICar car1 = new PassengerCar(1, 1);
        ICar car2 = new PassengerCar(2, 1);
        ICar truck1 = new TruckCar(3, 5);
        ICar truck2 = new TruckCar(4, 5);
        parking.startParking(car1);
        parking.startParking(car2);
        parking.startParking(truck1);

        boolean result = parking.startParking(truck2);

        assertFalse(result);
    }

    @Test
    public void whenParkingTruckIfCarAndTruckParkingIsOccupiedResultIsFalse() {
        IParking parking = new CarAndTruckParking(
                new CarParking(1, 1),
                new TruckParking(2, 5)
        );
        ICar car1 = new PassengerCar(1, 1);
        ICar truck1 = new TruckCar(2, 5);
        ICar truck2 = new TruckCar(3, 5);
        ICar truck3 = new TruckCar(4, 5);
        parking.startParking(car1);
        parking.startParking(truck1);
        parking.startParking(truck2);

        boolean result = parking.startParking(truck3);

        assertFalse(result);
    }

    @Test
    public void whenFinishParkingTruckOnTruckParkingResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(1, 1),
                new TruckParking(10, 5)
        );
        ICar truck1 = new TruckCar(2, 5);
        parking.startParking(truck1);

        boolean result = parking.finishParking(truck1);

        assertTrue(result);
    }

    @Test
    public void whenFinishParkingTruckOnCarParkingResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(0, 5)
        );
        ICar truck1 = new TruckCar(2, 5);
        parking.startParking(truck1);

        boolean result = parking.finishParking(truck1);

        assertTrue(result);
    }

    @Test
    public void whenFinishParkingTruckOnCarAndTruckParkingResultIsTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(2, 1),
                new TruckParking(3, 5)
        );
        ICar truck1 = new TruckCar(2, 5);
        parking.startParking(truck1);

        boolean result = parking.finishParking(truck1);

        assertTrue(result);
    }

    @Test
    public void whenFinishParkingTruckResultIsFalse() {
        IParking parking = new CarAndTruckParking(
                new CarParking(1, 1),
                new TruckParking(1, 5)
        );
        ICar truck1 = new TruckCar(2, 5);
        ICar truck2 = new TruckCar(3, 5);
        parking.startParking(truck1);
        parking.startParking(truck2);

        boolean result = parking.finishParking(truck2);

        assertFalse(result);
    }

    @Test
    public void whenFreeTruckSpacesIs5ResultTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(0, 1),
                new TruckParking(10, 5)
        );
        ICar truck1 = new TruckCar(2, 5);
        parking.startParking(truck1);

        boolean result = parking.checkFreeSpace();

        assertTrue(result);

    }

    @Test
    public void whenFreeCarAndTruckSpacesIs6ResultTrue() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(6, 5)
        );
        ICar truck1 = new TruckCar(2, 5);
        parking.startParking(truck1);

        boolean result = parking.checkFreeSpace();

        assertTrue(result);
    }

    @Test
    public void whenFreeCarAndTruckSpacesIs6ResultIs6() {
        IParking parking = new CarAndTruckParking(
                new CarParking(5, 1),
                new TruckParking(2, 5)
        );
        ICar truck1 = new TruckCar(2, 5);
        parking.startParking(truck1);

        int result = parking.countFreeSpace();

        assertThat(result, is(6));
    }
}