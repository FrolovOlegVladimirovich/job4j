package ru.job4j.io.zip;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для валидации аргументов,
 * получаемых из консоли при запуске программы Zip-архиватор.
 */
public class ValidateArgs {
    private Map<String, String> arguments;

    public ValidateArgs(String[] args) throws IOException {
        if (args != null && args.length == 6) {
            arguments = new HashMap<>(Map.of(
                    "directoryKey", args[0],
                    "directory", args[1],
                    "excludeKey", args[2],
                    "exclude", args[3],
                    "outputKey", args[4],
                    "output", args[5]));
        } else {
            throw new IOException(
                    "Missing key value / wrong number of arguments!\n\n"
                    + "To start ZIP-archive enter the query using the following pattern: "
                    + "\njava -jar pack.jar -d c:\\project\\job4j\\ -e .java -o project.zip"
                    + "\n\n-d archived directory\n-e files excluded from the archive\n-o output zip-file name");
        }
    }

    /**
     * Валидация аргументов ключа и пути директории.
     * @return Архивируемая директория.
     * @throws IOException - в случае если ключ не соответствует шаблону "-d"
     * или директория отсутствует.
     */
    public String directory() throws IOException {
        if (!arguments.containsValue("-d")) {
            throw new IOException("Wrong key " + arguments.get("directoryKey") + "\nKey should be -d");
        }
        if (!new File(arguments.get("directory")).exists()) {
            throw new IOException("Error: No such file or directory "
                    + arguments.get("directory")
                    + "\nTo start ZIP-archive enter the query using the following pattern: "
                    + "java -jar pack.jar -d c:/project/job4j/ -e .java -o project.zip"
                    + "\n-d archived directory\n-e files excluded from the archive\n-o output zip-file name"
            );

        }
        return arguments.get("directory");
    }

    /**
     * Валидация аргумента ключа с расширением файла, исключаемого из архива.
     * @return Расширение файла.
     * @throws IOException - в случае если ключ не соответствует шаблону "-e".
     *
     * Переменная exclude (расширение файла) не проверяется. В случае, если файлы с заданным
     * расширением отсутствуют в директории (либо расширение некорректно),
     * происходит архивация всех файлов, без исключений.
     */
    public String exclude() throws IOException {
        if (!arguments.containsValue("-e")) {
            throw new IOException("Wrong key " + arguments.get("excludeKey") + "\nKey should be -e");
        }
        return arguments.get("exclude");
    }

    /**
     * Валидация аргументов ключа и пути/названия архива.
     * @return Путь/название архива с расширением .zip
     * @throws IOException - в случае если ключ не соответствует шаблону "-o"
     * или отсутствует директория/расширение .zip
     */
    public String output() throws IOException {
        if (!arguments.containsValue("-o")) {
            throw new IOException("Wrong key " + arguments.get("outputKey") + "\nKey should be -o");
        }
        if (!arguments.get("output").endsWith(".zip")) {
            throw new IOException("Wrong output file name/directory: " + arguments.get("output") + "\nFile name should be .zip");
        }
        return arguments.get("output");
    }
}