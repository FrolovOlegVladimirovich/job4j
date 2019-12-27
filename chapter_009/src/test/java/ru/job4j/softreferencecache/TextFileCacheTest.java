package ru.job4j.softreferencecache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TextFileCacheTest {

    @Test
    public void whenReadExistingFileThenGetFileText() {
        var cache = new TextFileCache("./src/test/java/ru/job4j/softreferencecache/");
        var expect = String.format("%s%n%s%n%s%n%s%n", "Name1", "Name2", "Name3", "Test");

        var result = cache.read("Names.txt");

        assertThat(result, is(expect));
    }

    @Test
    public void whenReadNonexistentFileThenResultIsNull() {
        var cache = new TextFileCache("./src/test/java/ru/job4j/softreferencecache/");

        assertNull(cache.read("NonexistentFile.txt"));
    }
}