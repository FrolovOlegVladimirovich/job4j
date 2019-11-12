package ru.job4j.xmlmagnit;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class ParserSAXTest {
    @Test
    public void testParse() throws IOException {
        String source =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<entries>\n"
                + "    <entry field=\"1\"/>\n"
                + "    <entry field=\"2\"/>\n"
                + "    <entry field=\"3\"/>\n"
                + "</entries>\n";
        File sourceFile = new File(System.getProperty("java.io.tmpdir") + "sourceXMLTest.xml");
        try (FileWriter writeSource = new FileWriter(sourceFile)) {
            writeSource.write(source);
        }
        ParserSAX parserSAX = new ParserSAX();
        Long result =  parserSAX.parse(sourceFile);

        assertThat(result, is(6L));
    }
}