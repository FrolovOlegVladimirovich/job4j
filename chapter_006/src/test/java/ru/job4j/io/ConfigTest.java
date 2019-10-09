package ru.job4j.io;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@SuppressWarnings("SpellCheckingInspection")
public class ConfigTest {
    final Config config = new Config("./src/main/java/ru/job4j/io/app.properties");

    @Test
    public void configToString() {
        String expect =
                "## PostgreSQL\n"
                        + "\n"
                        + "hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect\n"
                        + "hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio\n"
                        + "hibernate.connection.driver_class=org.postgresql.Driver\n"
                        + "hibernate.connection.username=postgres\n"
                        + "hibernate.connection.password=password";

        assertThat(config.toString(), is(expect));
    }

    @Test
    public void compareKeysAndValues() {
        Map<String, String> expect = new HashMap<>();
        expect.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        expect.put("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/trackstudio");
        expect.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        expect.put("hibernate.connection.username", "postgres");
        expect.put("hibernate.connection.password", "password");

        config.load();

        assertThat(config.getValues(), is(expect));
    }

    @Test
    public void getValueTest() {
        config.load();

        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }
}