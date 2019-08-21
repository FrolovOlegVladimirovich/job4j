package ru.job4j.stream;

import java.util.Objects;

/**
 * Адрес с соответствующими полями.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class Address {
    private String city;
    private String street;
    private int house;
    private int apartment;

    public Address(String city, String street, int house, int apartment) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }


    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Address{"
                + "city='"
                + city
                + '\''
                + ", street='"
                + street
                + '\''
                + ", house="
                + house
                + ", apartment="
                + apartment
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return house == address.house
                && apartment == address.apartment
                && city.equals(address.city)
                && street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, apartment);
    }
}