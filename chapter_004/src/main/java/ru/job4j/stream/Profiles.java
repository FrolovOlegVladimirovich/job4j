package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Составляет список адресов клиентов из списка анкет.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class Profiles {
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }
}