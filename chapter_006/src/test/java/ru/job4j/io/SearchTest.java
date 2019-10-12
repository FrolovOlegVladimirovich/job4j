package ru.job4j.io;

import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тест поиска всех файлов с заданными расширениями.
 *
 * В тесте участвует 10 файлов: 4 TXT, 4 RTF, 2 DOC.
 */
public class SearchTest {
    static private File[] files;
    static private final String LINE = File.separator;
    static private final String PATH = System.getProperty("java.io.tmpdir") + LINE + "searchTestStructure" + LINE;

    /**
     * Перед стартом всех тестов создает:
     * 1) папку PATH с именем searchTestStructure;
     * 2) массив папок с директориями;
     * 3) массив файлов с директориями.
     */
    @BeforeClass
    public static void makeStructure() {
        new File(PATH).mkdir();

        File[] folders = new File[] {
                new File(PATH + "1" + LINE + "1-1" + LINE + "1-1-1" + LINE),
                new File(PATH + "1" + LINE + "1-1" + LINE + "1-1-2" + LINE),
                new File(PATH + "1" + LINE + "1-2" + LINE + "1-2-1" + LINE),
        };
        Arrays.stream(folders).filter(folder -> !folder.exists()).forEach(File::mkdirs);
        files = new File[] {
                new File(PATH + LINE + "1" + LINE + "test.txt"),
                new File(PATH + LINE + "1" + LINE + "test.rtf"),
                new File(PATH + LINE + "1" + LINE + "1-1" + LINE + "test.doc"),
                new File(PATH + LINE + "1" + LINE + "1-2" + LINE + "test.txt"),
                new File(PATH + LINE + "1" + LINE + "1-2" + LINE + "test.rtf"),
                new File(PATH + LINE + "1" + LINE + "1-2" + LINE + "test2.rtf"),
                new File(PATH + LINE + "1" + LINE + "1-1" + LINE + "1-1-1" + LINE + "test2.txt"),
                new File(PATH + LINE + "1" + LINE + "1-1" + LINE + "1-1-2" + LINE + "test.txt"),
                new File(PATH + LINE + "1" + LINE + "1-1" + LINE + "1-1-2" + LINE + "test.rtf"),
                new File(PATH + LINE + "1" + LINE + "1-2" + LINE + "1-2-1" + LINE + "test.doc")
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
     * После исполнения всех тестов удаляет:
     * 1) все файлы из массива files;
     * 2) все директории созданные в makeStructure().
     *
     * Поиск папок через метод обхода дерева в ширину.
     */
    @AfterClass
    public static void deleteStructure() {
        Arrays.stream(files).forEach(File::delete);
        List<File> result = new ArrayList<>();
        File start = new File(PATH);
        Queue<File> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                result.add(file);
            }
            File[] files = file.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(queue::offer);
            }
        }
        Collections.reverse(result);
        result.forEach(File::delete);
    }

    @Test
    public void whenSearchAllExtensionsResultIs10() {
        Search search = new Search();
        List<String> listExtensions = List.of("txt", "rtf", "doc");
        HashSet<File> result = new HashSet<>(search.files(PATH, listExtensions));
        HashSet<File> expect = new HashSet<>(Arrays.asList(files));

        assertThat(result.size(), is(10));
        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchRTFResultIs4() {
        Search search = new Search();
        List<String> listExtensions = List.of("rtf");
        HashSet<File> result = new HashSet<>(search.files(PATH, listExtensions));
        HashSet<File> expect = Arrays.stream(files).filter(file -> file.getName().endsWith(".rtf")).collect(Collectors.toCollection(HashSet::new));

        assertThat(result.size(), is(4));
        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchTXTResultIs4() {
        Search search = new Search();
        List<String> listExtensions = List.of("txt");
        HashSet<File> result = new HashSet<>(search.files(PATH, listExtensions));
        HashSet<File> expect = Arrays.stream(files).filter(file -> file.getName().endsWith(".txt")).collect(Collectors.toCollection(HashSet::new));

        assertThat(result.size(), is(4));
        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchDOCResultIs2() {
        Search search = new Search();
        List<String> listExtensions = List.of("doc");
        HashSet<File> result = new HashSet<>(search.files(PATH, listExtensions));
        HashSet<File> expect = Arrays.stream(files).filter(file -> file.getName().endsWith(".doc")).collect(Collectors.toCollection(HashSet::new));

        assertThat(result.size(), is(2));
        assertThat(result, is(expect));
    }
}