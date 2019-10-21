package ru.job4j.io.socket.wiseoracle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import static ru.job4j.io.socket.wiseoracle.Client.STOP_WORD;

/**
 * Чат-бот: серверная сторона на базе сокета.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 21.10.2019
 * @version 1.0
 */
public class Server {
    private final Socket socket;
    private final HashMap<String, String> oracleBase = new HashMap<>();

    public Server(Socket socket) {
        this.socket = socket;
        fillOracleBase();

    }

    /**
     * База ответов бота Оракула.
     * Ключ: вопрос.
     * Значение: ответ.
     */
    private void fillOracleBase() {
        oracleBase.put("привет", "Здравствуй, друг! Я Мудрый Оракул.");
        oracleBase.put("как дела?", "Чудесно! Как твои?");
        oracleBase.put("плохо", "Что случилось, друг мой?");
        oracleBase.put("отлично", "Рад за тебя, друг мой!");
        oracleBase.put("хорошо", "Прекрасно, друг мой!");
    }

    /**
     * Старт сервера.
     */
    public void start() {
        System.out.println("Подключение к серверу состоялось.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String message = in.readLine();
            while (!message.equals(STOP_WORD)) {
                System.out.printf("Получено сообщение от клиента: %s%n", message);
                message = oracleBase.getOrDefault(message, "Мне нечего тебе сказать...");
                out.write(message + System.lineSeparator());
                out.flush();
                System.out.printf("Отправлено сообщение клиенту: %s%n", message);
                message = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск программы c портом 5000.
     * @param args Аргументы не используются.
     */
    public static void main(String[] args) {
        System.out.println("Ожидание подключения к серверу...");
        try (Socket socket = new ServerSocket(5000).accept()) {
            new Server(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}