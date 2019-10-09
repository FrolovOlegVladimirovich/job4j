package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Анализ доступности сервера.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.10.2019
 * @version 1.0
 */
public class Analysis {

    /**
     * Анализирует данные из файла source и сохраняет периоды времени,
     * когда сервер был не доступен, в файле target.
     *
     * Файл server.log регистрирует события сервера в формате TYPE date.
     * Type - может иметь 4 значения 200, 300, 400, 500.
     * Date - это время проверки 10:56:01 (формат часы:минуты:секунды).
     *
     * Сервер не работал, если status == 400 или 500.
     * Начальное время - это время когда статус 400 или 500.
     * Конечное время это когда статус меняется с 400 или 500 на 200 или 300.
     *
     * @param source Исходный файл с логом сервера с расширением .log
     * @param target Файл с результатами (периоды времени, когда сервер
     *               был не доступен) с расширением .csv
     */
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            List<String> list = reader.lines().filter(line -> !line.isBlank()).collect(Collectors.toList());
            String start = null;
            String end = null;
            for (String item: list) {
                if (start == null && (item.startsWith("400") || item.startsWith("500"))) {
                    String[] arr = item.split(" ");
                    start = arr[1];
                } else if (start != null && !(item.startsWith("400") || item.startsWith("500"))) {
                    String[] arr = item.split(" ");
                    end = arr[1];
                }
                if (start != null && end != null) {
                    out.println(start + ";" + end);
                    start = null;
                    end = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}