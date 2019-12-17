package ru.job4j.simplegenerator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Text generator from template and values.
 */
public class SimpleGenerator implements Template {
    private final Pattern keys = Pattern.compile("(?=\\$\\{)(.*?})");

    @Override
    public String generate(String template, Map<String, String> data) throws Exception {
        Set<String> usedKeys = new HashSet<>();
        Matcher matcher = keys.matcher(template);
            while (matcher.find()) {
                String key = matcher.group();
                if (!data.containsKey(key)) {
                    throw new Exception("No key in data map");
                }
                template = template.replaceFirst(keys.pattern(), data.get(key));
                usedKeys.add(key);
            }
            if (!usedKeys.containsAll(data.keySet())) {
                throw new Exception("Data map contains redundant key");
            }
        return template;
    }
}