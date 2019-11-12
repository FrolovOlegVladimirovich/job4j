package ru.job4j.xmlmagnit;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class StoreXMLTest {

    @Test
    public void testSave() throws IOException {
        File target = new File(System.getProperty("java.io.tmpdir") + "sourceXML.xml");
        StoreXML storeXML = new StoreXML(target);
        List<Entry> entryList = List.of(new Entry(1), new Entry(2), new Entry(3));
        storeXML.save(entryList);
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileReader(target))) {
            while (scanner.hasNext()) {
                result.append(scanner.nextLine()).append(System.lineSeparator());
            }
        }
        String expect =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<entries>\n"
                + "    <entry>\n"
                + "        <field>1</field>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <field>2</field>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <field>3</field>\n"
                + "    </entry>\n"
                + "</entries>\n";

        assertThat(result.toString(), is(expect));
    }
}