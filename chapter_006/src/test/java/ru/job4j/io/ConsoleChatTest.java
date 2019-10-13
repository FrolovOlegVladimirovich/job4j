package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тест консольного чата.
 *
 */
public class ConsoleChatTest {
    private final String line = System.lineSeparator();
    private final List<String> botPhrases = List.of("Привет!", "Как дела?", "Что делаешь?", "Меня зовут Бот.");
    private final List<String> userPhrases = List.of("Олег", "Привет", "Как дела, бот?", "стоп", "Молчишь?", "продолжить", "закончить");

    /**
     * Тестирует работу чата, сравнивая лог чата и ожидаемый результат.
     *
     * 1) Создает базу данных ответов бота;
     * 2) Передает базу ответов бота и поток ответов пользователя в chat и запускает программу ConsoleChat;
     * 3) Считывает лог чата и ответы бота отдельно(для подстановки в ожидаемый результат);
     * 4) Сранивает result и expect.
     *
     * @throws IOException Возможное исключение IO.
     */
    @Test
    public void chatTest() throws IOException {
        File dataBase = new File(System.getProperty("java.io.tmpdir") + File.separator + "testdatabase.txt");
        try (BufferedWriter fillDataBase = new BufferedWriter(new PrintWriter(dataBase))) {
            for (String phrase: botPhrases) {
                fillDataBase.write(phrase + line);
            }
        }
        ConsoleChat chat;
        try (BufferedReader input = new BufferedReader(new StringReader(userPhrases.stream()
                .reduce((phrase1, phrase2) -> phrase1 + line + phrase2)
                .get()))) {
            chat = new ConsoleChat(dataBase.getPath(), input);
            chat.start();
        }
        File log = chat.getChatLog();
        List<String> logList;
        try (BufferedReader readLog = new BufferedReader(new FileReader(log))) {
            logList = readLog.lines().skip(2).collect(Collectors.toList());
        }
        List<String> botAnswers = logList.stream()
                .filter(string -> string.startsWith("Bot: "))
                .map(string -> string.replaceFirst("Bot: ", ""))
                .collect(Collectors.toList());

        String result = logList.stream().reduce((str1, str2) -> str1 + line + str2).get();
        String expect = "-------Добро пожаловать в консольный чат!-------"
                + line
                + line + "Введите ваше имя: Олег" + line
                + line + "Спасибо! Можете начинать общение." + line
                + line + "Олег: Привет"
                + line + "Bot: " + botAnswers.get(0)
                + line + "Олег: Как дела, бот?"
                + line + "Bot: " + botAnswers.get(1)
                + line + "Олег: стоп"
                + line + "Олег: Молчишь?"
                + line + "Олег: продолжить"
                + line + "Bot: " + botAnswers.get(2)
                + line + "Олег: закончить";

        assertThat(result, is(expect));
        log.delete();
        dataBase.delete();
    }
}