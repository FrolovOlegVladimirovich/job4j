package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * Сканирование файловой системы.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 12.10.2019
 * @version 1.0
 */
public class Search {

    /**
     * Поиск всех файлов с определенным расширением.
     *
     * Используется алгоритм обхода дерева в ширину.
     *
     * @param parent Путь до каталога, с которого начинается поиск.
     * @param extensions Список расширений файлов, которые необходимо найти.
     * @return Список найденных файлов.
     */
    public List<File> files(String parent, List<String> extensions) {
        List<File> result = new ArrayList<>();
        File start = new File(parent);
        Queue<File> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            File[] files = file.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(queue::offer);
            } else if (extensions.stream().anyMatch(ext -> file.getName().endsWith(ext))) {
                result.add(file);
            }
        }
        return result;
    }
}