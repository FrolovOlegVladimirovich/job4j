package ru.job4j.xmlmagnit;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class ConfigTest {
    @Test
    public void test() {
        Config config = new Config();
        config.init();
        Set<String> result = Set.of(
                config.get("url"),
                config.get("username"),
                config.get("password"),
                config.get("tablename"));
        Set<String> expect = Set.of(
                "jdbc:sqlite:",
                "sqlite",
                "password",
                "entries");

        assertThat(result, is(expect));
    }
}