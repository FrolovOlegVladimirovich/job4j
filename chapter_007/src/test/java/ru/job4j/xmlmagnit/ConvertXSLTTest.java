package ru.job4j.xmlmagnit;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class ConvertXSLTTest {

    @Test
    public void testConvert() throws IOException {
        String source =
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
        String expect =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<entries>\n"
                + "    <entry field=\"1\"/>\n"
                + "    <entry field=\"2\"/>\n"
                + "    <entry field=\"3\"/>\n"
                + "</entries>\n";
        File sourceFile = new File(System.getProperty("java.io.tmpdir") + "sourceXMLTest.xml");
        File resultFile = new File(System.getProperty("java.io.tmpdir") + "resultXMLTest.xml");
        File schemeFile = new File(Objects.requireNonNull(
                Start.class.getClassLoader().getResource("schema.xsl")).getPath());
        try (FileWriter writeSource = new FileWriter(sourceFile)) {
            writeSource.write(source);
        }
        new ConvertXSLT().convert(sourceFile, resultFile, schemeFile);
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(resultFile)) {
            while (scanner.hasNext()) {
                result.append(scanner.nextLine()).append(System.lineSeparator());
            }
        }

        assertThat(result.toString(), is(expect));
    }
}