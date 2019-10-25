package ru.job4j.io.searchfile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.job4j.io.searchfile.SearchFile.LN;

/**
 * Класс для валидации аргументов, получаемых из консоли
 * при запуске программы поиска по критерию.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 25.10.2019
 * @version 1.0
 */
public class ValidateArgs {
    private Map<String, String> arguments;

    public ValidateArgs(String[] args) throws IOException {
        if (args != null && args.length == 7) {
            arguments = new HashMap<>(Map.of(
                    "directoryKey", args[0],
                    "directory", args[1],
                    "nameKey", args[2],
                    "name", args[3],
                    "searchTypeKey", args[4],
                    "output", args[5],
                    "log", args[6]));
        } else {
            throw new IOException("Missing key value!" + LN + LN
                    + "To start a file search enter the query using the following pattern: "
                    + "java -jar find.jar -d directory -n name -m/-f/-r -o log.txt"
                    + LN + "-d search directory"
                    + LN + "-n file name, mask or regular expression"
                    + LN + "-m search by mask"
                    + LN + "-f full name match"
                    + LN + "-r regular expression"
                    + LN + "-o log file .txt" + LN);
        }
    }

    /**
     * Валидация аргументов ключа и пути директории.
     * @return Директория, в которой производится поиск.
     * @throws IOException - в случае если ключ не соответствует шаблону "-d"
     * или директория отсутствует/не является директорией.
     */
    public String directory() throws IOException {
        if (!arguments.containsValue("-d")) {
            throw new IOException("Wrong key " + arguments.get("directoryKey") + LN + "Key should be -d");
        }
        if (!new File(arguments.get("directory")).exists() || !new File(arguments.get("directory")).isDirectory()) {
            throw new IOException("Error: No such file or directory "
                    + arguments.get("directory") + LN
                    + "To start a file search enter the query using the following pattern: "
                    + "java -jar find.jar -d directory -n name -m/-f/-r -o log.txt"
                    + LN + "-d search directory"
                    + LN + "-n file name, mask or regular expression"
                    + LN + "-m search by mask"
                    + LN + "-f full name match"
                    + LN + "-r regular expression"
                    + LN + "-o log file .txt"
            );
        }
        return arguments.get("directory");
    }

    /**
     * Валидация аргумента ключа с именем, маской, регулярным выражением.
     * @return Ключ -n.
     * @throws IOException - в случае если ключ не соответствует шаблону "-n".
     */
    public String name() throws IOException {
        if (!arguments.containsValue("-n")) {
            throw new IOException("Wrong key " + arguments.get("nameKey") + LN + "Key should be -n");
        }
        return arguments.get("name");
    }

    /**
     * Валидация аргумента ключа с типом поиска.
     * @return Ключ -m/-f/-r.
     * @throws IOException - в случае если ключ не соответствует шаблону "-m/-f/-r".
     */
    public String typeSearch() throws IOException {
        if (!(arguments.containsValue("-m") || arguments.containsValue("-f") || arguments.containsValue("-r"))) {
            throw new IOException("Wrong key " + arguments.get("searchTypeKey") + LN + "Key should be -m/-f/-r");
        }
        return arguments.get("searchTypeKey");
    }

    /**
     * Валидация аргументов ключа и пути/названия лог-файла.
     * @return Путь/название файла с расширением .txt
     * @throws IOException - в случае если ключ не соответствует шаблону "-o"
     * или отсутствует директория/расширение .txt
     */
    public String log() throws IOException {
        if (!arguments.containsValue("-o")) {
            throw new IOException("Wrong key " + arguments.get("output") + LN + "Key should be -o");
        }
        if (!arguments.get("log").endsWith(".txt")) {
            throw new IOException("Wrong output file name/directory: " + arguments.get("log") + LN + "File name should be .txt");
        }
        return arguments.get("log");
    }
}