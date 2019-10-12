package ru.job4j.io.zip;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip-архиватор.
 *
 * Набор методов для архивации файла/директории с возможностью
 * исключения из архива файлов с заданным расширением.
 */
public class Zip {
    private final String root;
    private final String extension;
    private final File target;

    /**
     * @param root - файл/директория для архивации.
     * @param extension - расширение файлов, исключаемых из архивации.
     * @param target - путь/название архива с расширением .zip
     */
    public Zip(String root, String extension, String target) {
        this.root = root;
        this.extension = extension;
        this.target = new File(target);
    }

    /**
     * Поиск всех файлов для архивации в директории root,
     * за исключением всех файлов с расширением extension.
     *
     * Для поиска используется обход дерева в ширину.
     *
     * @return Список архивируемых файлов.
     */
    List<File> seekBy() {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(root));
        while (!queue.isEmpty()) {
            File file = queue.poll();
            File[] files = file.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(queue::offer);
            } else if (!file.getName().endsWith(extension) && !file.isHidden() && file.exists()) {
                result.add(file);
            }
        }
        return result;
    }

    /**
     * Архиватор файлов.
     *
     * @param sources Список архивируемых файлов.
     * @throws IOException - обработка исключений в классе StartZip.
     */
    public void pack(List<File> sources) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            Queue<File> queue = new LinkedList<>(sources);
            while (!queue.isEmpty()) {
                File file = queue.poll();
                String path = file.getPath().replaceFirst(root, "");
                zip.putNextEntry(new ZipEntry(path));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        }
    }
}