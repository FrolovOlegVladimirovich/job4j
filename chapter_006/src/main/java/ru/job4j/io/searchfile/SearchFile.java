package ru.job4j.io.searchfile;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Поиск файлов по критерию.
 *
 * Поиск файлов по полному совпадению имени, маске, регулярному выражению.
 * Запись результатов производится в лог.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 25.10.2019
 * @version 1.0
 */
public class SearchFile {
    static final String LN = System.lineSeparator();
    private final String root;
    private final String name;
    private final String searchType;
    private final File log;

    /**
     *
     * @param root Директория, в которой производится поиск.
     * @param name Имя искомого файла/маска/регулярное выражение.
     * @param searchType Тип поиска. Ключи "-m"/"-f"/"-r":
     *                   поиск по маске/полному совпадению имени/регулярному выражению.
     * @param log Путь/название файла с расширением .txt для записи результатов поиска.
     */
    public SearchFile(String root, String name, String searchType, String log) {
        this.root = root;
        this.name = name;
        this.searchType = searchType;
        this.log = new File(log);
    }

    /**
     * Поиск всех файлов в директории root,
     * соответствующих name и типу поиска searchType.
     *
     * Для поиска используется обход дерева в ширину.
     *
     * @return Список найденных файлов.
     */
    private List<File> search() {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(root));
        while (!queue.isEmpty()) {
            File file = queue.poll();
            File[] files = file.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(queue::offer);
            } else if (!file.isHidden() && file.exists()) {
                if (searchType.equals("-m") && mask(file)) {
                    result.add(file);
                } else if (searchType.equals("-f") && file.getName().equals(name)) {
                    result.add(file);
                } else if (searchType.equals("-r") && regex(file)) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    /**
     * Сравнивает имя файла с паттерном регулярного выражения.
     * @param file Файл, проверяемый на совпадение.
     * @return true - если файл совпадает с паттерном регулярного выражения.
     */
    private boolean regex(File file) {
        return Pattern.compile(name).matcher(file.getName()).find();
    }

    /**
     * Сравнивает имя файла в зависимости от типа маски.
     * @param file Файл, проверяемый на совпадение.
     * @return true - если файл совпадает с критерием поиска.
     */
    private boolean mask(File file) {
        String name = this.name.replaceAll("\\*", "");
        if (this.name.startsWith("*") && this.name.endsWith("*")) {
            return file.getName().contains(name);
        } else if (this.name.startsWith("*")) {
            return file.getName().endsWith(name);
        } else if (this.name.endsWith("*")) {
            return file.getName().startsWith(name);
        }
        return false;
    }

    /**
     * Запускает поиск файлов и записывает результаты в лог.
     */
    private void start() {
        List<File> result = search();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(log))) {
            for (File file: result) {
                bufferedWriter.write(file.getPath() + LN);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("Error: No such log file or directory");
            e.printStackTrace();
        }
    }

    /**
     * Запуск программы.
     *
     * @param args:
     * [0] - ключ "-d" - директория, в которой производится поиск;
     * [1] - путь к директории поиска;
     * [2],[3] - ключ "-n" - имя файла/маска/регулярное выражение.
     * [4] - ключи "-m"/"-f"/"-r" - поиск по маске/полному совпадению имени/регулярному выражению.
     * [5],[6]- ключ "-o" - путь/название файла с расширением .txt для записи результатов поиска.
     */
    public static void main(String[] args) {
        try {
            ValidateArgs validArgs = new ValidateArgs(args);
            SearchFile searchFile = new SearchFile(validArgs.directory(), validArgs.name(), validArgs.typeSearch(), validArgs.log());
            searchFile.start();
        } catch (IOException exception) {
            String ex = exception.toString().replaceFirst("java.io.IOException: ", "");
            System.out.print(ex);
            return;
        }
        System.out.print("You can find the search results in the log file.");
    }
}