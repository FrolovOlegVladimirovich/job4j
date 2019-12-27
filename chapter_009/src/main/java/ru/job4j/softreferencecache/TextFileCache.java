package ru.job4j.softreferencecache;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.*;

/**
 * Text files cache using SoftReference.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class TextFileCache {
    private final Map<String, SoftReference<String>> cache;
    private final String directory;

    /**
     * Default constructor.
     * @param directory Cached directory.
     */
    public TextFileCache(String directory) {
        this.cache = new HashMap<>();
        this.directory = directory;
    }

    /**
     * Reads value from cache.
     * @param key Key.
     * @return Value.
     */
    public String read(String key) {
        return !cache.containsKey(key) && add(key) ? cache.get(key).get() : null;
    }

    /**
     * Adds the text of the file to the cache.
     * @param fileName File name.
     * @return true, if the file was successfully added to the cache.
     */
    private boolean add(String fileName) {
        var found = search(fileName);
        if (found.isPresent()) {
            cache.put(fileName, new SoftReference<>(getText(found.get())));
            return true;
        }
        return false;
    }

    /**
     * Searches for a file in a directory by the given name.
     * @param fileName File name.
     * @return Optional container.
     */
    private Optional<File> search(String fileName) {
        return Arrays
                .stream(Objects.requireNonNull(new File(directory).listFiles()))
                .filter(file -> fileName.equals(file.getName()))
                .findFirst();
    }

    /**
     * Reads text from file.
     * @param file File.
     * @return the text that is read from the file.
     */
    private String getText(File file) {
        var text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(line -> {
                        text.append(line);
                        text.append(System.lineSeparator());
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}