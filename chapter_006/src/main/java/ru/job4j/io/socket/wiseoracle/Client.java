package ru.job4j.io.socket.wiseoracle;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Чат-бот: клиентская сторона на базе сокета.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 21.10.2019
 * @version 1.0
 */
public class Client {
    private final Socket socket;
    private static final String LN = System.lineSeparator();
    static final String STOP_WORD = "пока";

    public Client(Socket socket) {
        this.socket = socket;
    }

    /**
     * Старт клиента.
     *
     * @param console Текстовые строки, введенные пользователем с клавиатуры в консоль.
     */
    public void start(Scanner console) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            String name = console.nextLine();
            System.out.printf("Спасибо, %s! Можете начинать.%n%n", name);
            String message;
            do {
                System.out.print(name + ": ");
                message = console.nextLine();
                out.write(message + LN);
                out.flush();
                if (!message.equals(STOP_WORD)) {
                    System.out.printf("Оракул: %s%n", in.readLine());
                }
            } while (!message.equals(STOP_WORD));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск программы c портом 5000 и локальным хостом 127.0.0.1.
     * @param args Аргументы не используются.
     */
    public static void main(String[] args) {
        System.out.printf("Добро пожаловать в чат с Мудрым Оракулом!%n%nПожалуйста, представьтесь: ");
        try (Scanner console = new Scanner(System.in);
             Socket socket = new Socket("127.0.0.1", 5000)) {
            Client client = new Client(socket);
            client.start(console);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}