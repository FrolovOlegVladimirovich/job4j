package ru.job4j.stream;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест создания списка адресов клиентов из списка анкет.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.08.2019
 * @version 1.0
 */
public class ProfilesTest {
    private final Profiles profiles = new Profiles();
    private final List<Profile> profilesList = List.of(
            new Profile(new Address("Moscow", "Pravdy", 10, 121)),
            new Profile(new Address("New York", "Washington Ave.", 141, 763)),
            new Profile(new Address("Tula", "8 Marta", 13, 32))
    );

    @Test
    public void getAdressessToList() {
        List<Address> result = List.of(new Address("Moscow", "Pravdy", 10, 121),
                new Address("New York", "Washington Ave.", 141, 763),
                new Address("Tula", "8 Marta", 13, 32));
        assertThat(profiles.collect(profilesList), is(result));
    }
}