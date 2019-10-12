package ru.job4j.io.zip;

import java.io.IOException;

/**
 * Класс для запуска программы Zip-архиватора.
 */
public class StartZip {

    /**
     * @param args:
     * [0] - ключ "-d" - архивируемая директория;
     * [1] - путь к архивируемой директории;
     * [2] - ключ "-e" - формат файлов, исключаемых из архива.
     * [3] - формат файла (например: .doc);
     * [4] - ключ -o - директория/название файла в который архивируем.
     * [5] - путь/название файла с расширением .zip
     */
    public static void main(String[] args) {
        try {
            ValidateArgs validArgs = new ValidateArgs(args);
            Zip zip = new Zip(validArgs.directory(), validArgs.exclude(), validArgs.output());
            zip.pack(zip.seekBy());
        } catch (IOException exception) {
            String ex = exception.toString().replaceFirst("java.io.IOException: ", "");
            System.out.print(ex);
            return;
        }
        System.out.print("ZIP-archive created successfully.");
    }
}