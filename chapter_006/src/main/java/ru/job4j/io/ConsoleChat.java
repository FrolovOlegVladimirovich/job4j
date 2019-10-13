package ru.job4j.io;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Консольный чат.
 *
 * Пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * Программа замолкает, если пользователь вводит слово «стоп», при этом, он может продолжать отправлять сообщения в чат.
 * Если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * При вводе слова «закончить» программа прекращает работу.
 * Запись диалога включая, слова-команды стоп/продолжить/закончить записываются в текстовый лог.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.10.2019
 * @version 1.0
 */
public class ConsoleChat {
    private String userName;
    private final String line = System.lineSeparator();
    private final String slash = File.separator;
    private final File answersDatabase;
    private final File chatLog = new File(System.getProperty("java.io.tmpdir") + slash + "chat_log.txt");
    private final BufferedReader input;

    /**
     * Конструктор по умолчанию.
     * Использует готовую базу данных ответов бота.
     */
    public ConsoleChat() {
        this.answersDatabase = new File("/Users/Oleg/Documents/GitHub/job4j/chapter_006/src/main/java/ru/job4j/io/chat/answers_database.txt");
        this.input = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Конструктор с параметром:
     * @param answersDatabase Адрес базы данных ответов бота.
     */
    public ConsoleChat(String answersDatabase) {
        this.answersDatabase = new File(answersDatabase);
        this.input = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Конструктор с параметрами:
     * @param answersDatabase Адрес базы данных ответов бота.
     * @param inputUserPhrases Буфферизованный поток данных с фразами пользователя.
     *
     * Используется для теста.
     */
    public ConsoleChat(String answersDatabase, BufferedReader inputUserPhrases) {
        this.answersDatabase = new File(answersDatabase);
        this.input = inputUserPhrases;

    }

    /**
     * Возвращает файл с логом чата.
     * @return Возвращает файл с логом чата.
     */
    public File getChatLog() {
        return chatLog;
    }

    /**
     * Возвращает рандомный ответ бота из базы данных фраз.
     *
     * @return Ответ бота.
     */
    private String takeRandomPhrase() {
        List<String> answers;
        String result = null;
        try (BufferedReader buffer = new BufferedReader(new FileReader(answersDatabase))) {
            answers = buffer.lines().collect(Collectors.toList());
            int random = (int) (0 + (Math.random() * answers.size()));
            result = answers.get(random);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Записывает в лог ответы пользователя.
     *
     * @param input Буфферизованный поток данных из консоли.
     * @param logging Буфферизованный поток данных для записи в лог.
     * @return Ответ пользователя.
     */
    private String userLog(BufferedReader input, BufferedWriter logging) {
        String line = null;
        try {
            line = input.readLine();
            logging.write(userName + ": " + line + this.line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * Записывает в лог ответы бота.
     *
     * @param logging Буфферизованный поток данных для записи в лог.
     */
    private void botLog(BufferedWriter logging) {
        try {
            String answer;
            answer = takeRandomPhrase();
            System.out.println("Bot: " + answer);
            logging.write("Bot: " + answer + this.line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Стартовое приветствие для чата с получением имени пользователя.
     *
     * @param input Буфферизованный поток данных из консоли.
     * @param logging Буфферизованный поток данных для записи в лог.
     * @throws IOException Если происходит ошибка ввода-вывода.
     */
    private void startChat(BufferedReader input, BufferedWriter logging) throws IOException {
        final String welcome = "-------Добро пожаловать в консольный чат!-------"
                + line + line + "Введите ваше имя: ";
        final String message = line + "Спасибо! Можете начинать общение." + line;
        final String instruction = "Чтобы бот замолчал введите: стоп"
                + line
                + "Чтобы продолжить общение с ботом введите: продолжить"
                + line
                + "Для выхода из чата введите: закончить"
                + line;
        logging.write(LocalDateTime.now().toString() + line + line);
        System.out.print(welcome);
        logging.write(welcome);
        userName = input.readLine();
        logging.write(userName);
        System.out.println(message);
        logging.write(line + message + line);
        System.out.println(instruction);
    }

    /**
     * Проверяет ответ пользователя на ключевые слова
     * для остановки/продолжения бота/закрытия чата.
     *
     * @param input Буфферизованный поток данных из консоли.
     * @param logging Буфферизованный поток данных для записи в лог.
     */
    private void userAnswerCheck(BufferedReader input, BufferedWriter logging) {
        String answer;
        final String CLOSE = "закончить";
        final String STOP = "стоп";
        final String CONTINUE = "продолжить";
        boolean stopTalking = false;
        do {
            System.out.print(userName + ": ");
            answer = userLog(input, logging);
            if (answer.equals(STOP)) {
                stopTalking = true;
            }
            if (answer.equals(CONTINUE)) {
                stopTalking = false;
            }
            if (!answer.equals(CLOSE) && !stopTalking) {
                botLog(logging);
            }
        } while (!answer.equals(CLOSE));
    }

    /**
     * Открывает потоки ввода-вывода с вызовом необходимых методов для работы чата.
     */
    public void start() {
        try (BufferedReader input = this.input;
             BufferedWriter logging = new BufferedWriter(new FileWriter(chatLog))) {
            startChat(input, logging);
            userAnswerCheck(input, logging);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск программы
     * @param args Без параметров.
     */
    public static void main(String[] args) {
        new ConsoleChat().start();
    }
}