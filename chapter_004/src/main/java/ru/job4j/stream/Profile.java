package ru.job4j.stream;

import java.util.Objects;

/**
 * Профиль анкеты с полем адрес.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class Profile {
    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Profile{"
                + "address="
                + address
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
        Profile profile = (Profile) o;
        return address.equals(profile.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}