package ru.job4j.io.zip;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты Zip-архиватора.
 */
public class ZipTest {
    private static final String LINE = System.lineSeparator();

    @Test
    public void whenPackAllFilesExcludingJAVAResultIsAllFilesWithoutJAVA() throws IOException {
        StartZip.main(new String[] {
                "-d", System.getProperty("user.dir"),
                "-e", ".java",
                "-o", System.getProperty("java.io.tmpdir") + "/Arch.zip"
        });

        Zip zip = new Zip(
                System.getProperty("user.dir"),
                "java",
                System.getProperty("java.io.tmpdir") + "/Arch.zip"
        );

        HashSet<String> expect = new HashSet<>();
        zip.seekBy().forEach(file -> expect.add(file.getPath().replace(
                System.getProperty("user.dir"), "")));
        HashSet<String> result = new HashSet<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(
                new FileInputStream(
                        new File(System.getProperty("java.io.tmpdir") + "/Arch.zip")))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                result.add(zipEntry.getName());
                zipEntry = zipInputStream.getNextEntry();
            }
        }

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongExtensionThenPackAllFiles() throws IOException {
        StartZip.main(new String[] {
                "-d", System.getProperty("user.dir"),
                "-e", ".WRONG",
                "-o", System.getProperty("java.io.tmpdir") + "/Arch.zip"
        });
        Zip zip = new Zip(
                System.getProperty("user.dir"),
                ".WRONG",
                System.getProperty("java.io.tmpdir") + "/Arch.zip"
        );

        HashSet<String> expect = new HashSet<>();
        zip.seekBy().forEach(file -> expect.add(file.getPath().replace(
                System.getProperty("user.dir"), "")));
        HashSet<String> result = new HashSet<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(
                new FileInputStream(
                        new File(System.getProperty("java.io.tmpdir") + "/Arch.zip")))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                result.add(zipEntry.getName());
                zipEntry = zipInputStream.getNextEntry();
            }
        }

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongInputDirectoryResultIsErrorMessage() {
        String[] args = new String[] {
                "-d", "Wrong_Directory",
                "-e", ".java",
                "-o", System.getProperty("java.io.tmpdir") + "/Arch.zip"
        };

        StartZip.main(args);

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Error: No such file or directory " + args[1]
                + LINE + "To start ZIP-archive enter the query using the following pattern: "
                + "java -jar pack.jar -d c:/project/job4j/ -e .java -o project.zip"
                + LINE + "-d archived directory"
                + LINE + "-e files excluded from the archive"
                + LINE + "-o output zip-file name";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongOutputDirectoryResultIsErrorMessage() {
        String[] args = new String[] {
                "-d", System.getProperty("user.dir"),
                "-e", ".java",
                "-o", "Wrong_Directory"
        };

        StartZip.main(args);

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong output file name/directory: " + args[5]
                + LINE + "File name should be .zip";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredMissingKeyValueResultIsErrorMessage() {
        String[] args = new String[] {
                "-d", System.getProperty("user.dir"),
                ".java",
                "-o", System.getProperty("java.io.tmpdir") + "/Arch.zip"
        };

        StartZip.main(args);

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Missing key value / wrong number of arguments!" + LINE
                + LINE + "To start ZIP-archive enter the query using the following pattern: "
                + LINE + "java -jar pack.jar -d c:\\project\\job4j\\ -e .java -o project.zip" + LINE
                + LINE + "-d archived directory" + LINE + "-e files excluded from the archive" + LINE
                + "-o output zip-file name";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongKeyEResultIsErrorMessage() {
        String[] args = new String[] {
                "-d", System.getProperty("user.dir"),
                "-E", ".java",
                "-o", System.getProperty("java.io.tmpdir") + "/Arch.zip"
        };
        StartZip.main(args);

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong key " + args[2] + LINE + "Key should be -e";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongKeyDResultIsErrorMessage() {
        String[] args = new String[] {
                "-D", System.getProperty("user.dir"),
                "-e", ".java",
                "-o", System.getProperty("java.io.tmpdir") + "/Arch.zip"
        };
        StartZip.main(args);

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong key " + args[0] + LINE + "Key should be -d";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongKeyOResultIsErrorMessage() {
        String[] args = new String[] {
                "-d", System.getProperty("user.dir"),
                "-e", ".java",
                "-O", System.getProperty("java.io.tmpdir") + "/Arch.zip"
        };
        StartZip.main(args);

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong key " + args[4] + LINE + "Key should be -o";

        assertThat(result, is(expect));
    }


    /**
     * Вспомогательный метод для запуска программы ZIP-архиватор
     * с подменой потока вывода в консоль в переменную потока байтов.
     *
     * @param args - Аргументы для метода main (для старта приложения):
     *             получает из вызывающего метод теста.
     * @return Поток байтов для теста с результатами вывода в консоль.
     */
    public ByteArrayOutputStream startAndReturnSystemOutput(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        StartZip.main(args);
        System.setOut(consoleStream);
        return outputStream;
    }
}