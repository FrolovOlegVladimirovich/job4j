package ru.job4j.stream;

import java.util.Comparator;
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

    /**
     * Создает список адресов клиентов из списка анкет.
     * @param profiles Список анкет.
     * @return Список адресов.
     */
    List<Address> collectAdressesFromProfilesToList(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }

    /**
     * Создает список уникальных адресов, отсортированных по полю String city.
     * @param profiles Список анкет.
     * @return Список адресов.
     */
    List<Address> collectUniqueAdressesSortedByCity(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .sorted(Comparator.comparing(Address::getCity))
                .distinct()
                .collect(Collectors.toList());
    }
}