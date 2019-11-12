package ru.job4j.xmlmagnit;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@Ignore
public class StoreSQLTest {

    @Test
    public void testInitConnection() {
        Config config = new Config();
        config.init();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.initConnection();
        Connection connection = storeSQL.getConnection();

        assertNotNull(connection);
    }

    @Test
    public void testGenerateAndLoad() {
        Config config = new Config();
        config.init();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.initConnection();
        storeSQL.generate(3);
        Set<Entry> result = Set.copyOf(storeSQL.load());
        Set<Entry> expect = Set.of(new Entry(1), new Entry(2), new Entry(3));

        assertThat(result, is(expect));
    }
}