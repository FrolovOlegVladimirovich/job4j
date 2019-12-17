package ru.job4j.simplegenerator;

import java.util.Map;

/**
 * Template contains method for replacing keys with specific words.
 */
public interface Template {

    /**
     * Generate text from map of values that replace keys in template.
     * @param template contains keys that will be replaced with values from the data map.
     * @param data A map with keys matching to template keys and values that should be instead of keys in the template.
     * @return Complete generated String.
     */
    String generate(String template, Map<String, String> data) throws Exception;
}