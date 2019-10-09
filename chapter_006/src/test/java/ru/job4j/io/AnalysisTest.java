package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Содержимое файла server1.log:
 * 200 10:56:01
 *
 * 500 10:57:01
 *
 * 400 10:58:01
 *
 * 200 10:59:01
 *
 * 500 11:01:02
 *
 * 200 11:02:02
 *
 * Содержимое файла server2.log:
 * 200 09:13:18
 *
 * 200 09:16:34
 *
 * 200 09:48:05
 *
 * 500 10:03:18
 *
 * 400 10:11:08
 *
 * 300 10:24:45
 *
 * 200 10:48:15
 *
 * 200 11:06:54
 *
 * 400 11:12:32
 *
 * 400 11:15:01
 *
 * 400 11:27:08
 *
 * 200 11:39:17
 *
 * 500 11:57:24
 *
 * 400 12:08:21
 *
 * 300 12:11:23
 *
 * 300 12:16:54
 *
 * 200 12:20:18
 *
 */
public class AnalysisTest {
    final String source1 = "./src/main/java/ru/job4j/io/server1.log";
    final String source2 = "./src/main/java/ru/job4j/io/server2.log";
    final String target = "./src/main/java/ru/job4j/io/unavailable.csv";

    @Test
    public void testServerLog() {
        Analysis analysis = new Analysis();
        analysis.unavailable(source1, target);

        List<String> expect = new ArrayList<>();
        expect.add("10:57:01;10:59:01");
        expect.add("11:01:02;11:02:02");

        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(result, is(expect));
    }

    @Test
    public void testServer2Log() {
        Analysis analysis = new Analysis();
        analysis.unavailable(source2, target);

        List<String> expect = new ArrayList<>();
        expect.add("10:03:18;10:24:45");
        expect.add("11:12:32;11:39:17");
        expect.add("11:57:24;12:11:23");

        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(result, is(expect));
    }
}