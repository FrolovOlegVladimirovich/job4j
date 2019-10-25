package ru.job4j.io.searchfile;

import org.junit.*;

import java.io.*;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты поиска файлов по критерию.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 25.10.2019
 * @version 1.0
 */
public class SearchFileTest {
    private static final String LINE = System.lineSeparator();
    private static final String SLASH = File.separator;
    private final String logPath = System.getProperty("java.io.tmpdir") + SLASH + "log.txt";
    private static final String PATH = System.getProperty("java.io.tmpdir")
            + SLASH + "SearchTestStructure" + SLASH;
    private static final File FILE1TXT = new File(PATH + SLASH + "Oleg"
            + SLASH + "test.txt");
    private static final File FILE2RTF = new File(PATH + SLASH + "Oleg"
            + SLASH + "test.rtf");
    private static final File FILE3DOC = new File(PATH + SLASH + "Oleg"
            + SLASH + "Project" + SLASH + "MyResults.doc");
    private static final File FILE4TXT = new File(PATH + SLASH + "Oleg"
            + SLASH + "Reports" + SLASH + "report.txt");
    private static final File FILE5RTF = new File(PATH + SLASH + "Oleg"
            + SLASH + "Reports" + SLASH + "report.rtf");
    private static final File FILE6RTF = new File(PATH + SLASH + "Oleg"
            + SLASH + "Reports" + SLASH + "report2.rtf");
    private static final File FILE7TXT = new File(PATH + SLASH + "Oleg"
            + SLASH + "Project" + SLASH + "log1" + SLASH + "test1.txt");
    private static final File FILE8TXT = new File(PATH + SLASH + "Oleg"
            + SLASH + "Project" + SLASH + "log2" + SLASH + "test2.txt");
    private static final File FILE9RTF = new File(PATH + SLASH + "Oleg"
            + SLASH + "Project" + SLASH + "log2" + SLASH + "test2.rtf");
    private static final File FILE10DOC = new File(PATH + SLASH + "Oleg"
            + SLASH + "Reports" + SLASH + "today" + SLASH + "MyPlan.doc");

    /**
     * Перед стартом всех тестов создает:
     * 1) папку PATH с именем SearchTestStructure;
     * 2) массив папок с директориями;
     * 3) массив файлов с директориями.
     */
    @BeforeClass
    public static void makeStructure() {
        new File(PATH).mkdir();

        File[] folders = new File[] {
                new File(PATH + "Oleg" + SLASH + "Project" + SLASH + "log1" + SLASH),
                new File(PATH + "Oleg" + SLASH + "Project" + SLASH + "log2" + SLASH),
                new File(PATH + "Oleg" + SLASH + "Reports" + SLASH + "today" + SLASH),
        };
        Arrays.stream(folders).filter(folder -> !folder.exists()).forEach(File::mkdirs);
        File[] files = new File[]{
                FILE1TXT,
                FILE2RTF,
                FILE3DOC,
                FILE4TXT,
                FILE5RTF,
                FILE6RTF,
                FILE7TXT,
                FILE8TXT,
                FILE9RTF,
                FILE10DOC
        };
        Arrays.stream(files).filter(file -> !file.exists()).forEach(file -> {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * После выполнения всех тестов удаляет все директории и файлы,
     * созданные в makeStructure();
     *
     * Поиск папок и файлов через метод обхода дерева в ширину.
     */
    @AfterClass
    public static void deleteStructure() {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(PATH));
        while (!queue.isEmpty()) {
            File file = queue.poll();
            File[] files = file.listFiles();
            result.add(file);
            if (files != null) {
                Arrays.stream(files).forEach(queue::offer);
            }
        }
        Collections.reverse(result);
        result.forEach(File::delete);
    }

    /**
     * Удаляет лог-файл после каждого теста.
     */
    @After
    public void deleteLogFile() {
        new File(logPath).delete();
    }

    @Test
    public void whenFindDOCFilesWithStartMaskResultIs2DOCFiles() throws IOException {
        String[] args = new String[] {"-d", PATH, "-n", "*.doc", "-m", "-o", logPath};
        SearchFile.main(args);

        HashSet<String> expect = new HashSet<>(List.of(
                FILE3DOC.getPath(),
                FILE10DOC.getPath()));
        HashSet<String> result = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logPath))) {
            bufferedReader.lines().forEach(result::add);
        }

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindFilesWithEndMaskResultIs2Files() throws IOException {
        String[] args = new String[] {"-d", PATH, "-n", "My*", "-m", "-o", logPath};
        SearchFile.main(args);

        HashSet<String> expect = new HashSet<>(List.of(
                FILE3DOC.getPath(),
                FILE10DOC.getPath()));
        HashSet<String> result = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logPath))) {
            bufferedReader.lines().forEach(result::add);
        }

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindFilesWithDoubleMaskResultIs3Files() throws IOException {
        String[] args = new String[] {"-d", PATH, "-n", "*report*", "-m", "-o", logPath};
        SearchFile.main(args);

        HashSet<String> expect = new HashSet<>(List.of(
                FILE4TXT.getPath(),
                FILE5RTF.getPath(),
                FILE6RTF.getPath()));
        HashSet<String> result = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logPath))) {
            bufferedReader.lines().forEach(result::add);
        }

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindFilesByFullNameResultIs1File() throws IOException {
        String[] args = new String[] {"-d", PATH, "-n", "test2.rtf", "-f", "-o", logPath};
        SearchFile.main(args);

        HashSet<String> expect = new HashSet<>(List.of(
                FILE9RTF.getPath()));
        HashSet<String> result = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logPath))) {
            bufferedReader.lines().forEach(result::add);
        }

        assertThat(result, is(expect));
    }

    @Test
    public void whenFindFilesWithRegexResultIs3Files() throws IOException {
        String[] args = new String[] {"-d", PATH, "-n", "(^test)(?=.*txt)", "-r", "-o", logPath};
        SearchFile.main(args);

        HashSet<String> expect = new HashSet<>(List.of(
                FILE1TXT.getPath(),
                FILE7TXT.getPath(),
                FILE8TXT.getPath()));
        HashSet<String> result = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logPath))) {
            bufferedReader.lines().forEach(result::add);
        }

        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchedFileNotFound() throws IOException {
        String[] args = new String[] {"-d", PATH, "-n", "super", "-f", "-o", logPath};
        SearchFile.main(args);

        HashSet<String> expect = new HashSet<>();
        HashSet<String> result = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logPath))) {
            bufferedReader.lines().forEach(result::add);
        }

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongKeyDResultIsErrorMessage() {
        String[] args = new String[] {"-D", PATH, "-n", "super", "-f", "-o", logPath};

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong key " + args[0] + LINE + "Key should be -d";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongKeyNResultIsErrorMessage() {
        String[] args = new String[] {"-d", PATH, "-N", "super", "-f", "-o", logPath};

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong key " + args[2] + LINE + "Key should be -n";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongKeySearchTypeResultIsErrorMessage() {
        String[] args = new String[] {"-d", PATH, "-n", "super", "-F", "-o", logPath};

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong key " + args[4] + LINE + "Key should be -m/-f/-r";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongKeyOResultIsErrorMessage() {
        String[] args = new String[] {"-d", PATH, "-n", "super", "-f", "-O", logPath};

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong key " + args[5] + LINE + "Key should be -o";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredMissingKeyValueResultIsErrorMessage() {
        String[] args = new String[] {"-d", PATH, "-n", "super", "-f", logPath};

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Missing key value!" + LINE + LINE
                + "To start a file search enter the query using the following pattern: "
                + "java -jar find.jar -d directory -n name -m/-f/-r -o log.txt"
                + LINE + "-d search directory"
                + LINE + "-n file name, mask or regular expression"
                + LINE + "-m search by mask"
                + LINE + "-f full name match"
                + LINE + "-r regular expression"
                + LINE + "-o log file .txt"
                + LINE;

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongLogFileResultIsErrorMessage() {
        String[] args = new String[] {"-d", PATH, "-n", "super", "-f", "-o", "WRONG_DIRECTORY"};

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Wrong output file name/directory: " + args[6]
                + LINE + "File name should be .txt";

        assertThat(result, is(expect));
    }

    @Test
    public void whenUserEnteredWrongSearchDirectoryResultIsErrorMessage() {
        String[] args = new String[] {"-d", "WRONG_DIRECTORY", "-n", "super", "-f", "-o", logPath};

        String result = startAndReturnSystemOutput(args).toString();
        String expect = "Error: No such file or directory "
                + args[1] + LINE
                + "To start a file search enter the query using the following pattern: "
                + "java -jar find.jar -d directory -n name -m/-f/-r -o log.txt"
                + LINE + "-d search directory"
                + LINE + "-n file name, mask or regular expression"
                + LINE + "-m search by mask"
                + LINE + "-f full name match"
                + LINE + "-r regular expression"
                + LINE + "-o log file .txt";

        assertThat(result, is(expect));
    }

    /**
     * Вспомогательный метод для запуска программы поиска файлов по критерию
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
        SearchFile.main(args);
        System.setOut(consoleStream);
        return outputStream;
    }
}