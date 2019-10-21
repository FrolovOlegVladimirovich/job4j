package ru.job4j.io.socket.wiseoracle;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест серверной стороны чат-бота через сокет.
 *
 * В тесте используются библиотеки Mockito и Guava.
 */
public class ServerTest {
    private final String ln = System.lineSeparator();

    private void testServer(String input, String expect) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();

        assertThat(out.toString(), is(expect));
    }

    @Test
    public void whenAskExitResultIsExitProgram() throws IOException {
        testServer("пока", "");
    }

    @Test
    public void whenAskAnyQuestionsReturnsCorrectAnswersFromDataBase() throws IOException {
        testServer(Joiner.on(ln).join(
                "привет",
                "как дела?",
                "плохо",
                "отлично",
                "хорошо",
                "пока"
                ),
                Joiner.on(ln).join(
                        "Здравствуй, друг! Я Мудрый Оракул.",
                        "Чудесно! Как твои?",
                        "Что случилось, друг мой?",
                        "Рад за тебя, друг мой!",
                        "Прекрасно, друг мой!",
                        ""
                ));

    }

    @Test
    public void whenAskQuestionNotInTheDataBase() throws IOException {
        testServer(Joiner.on(ln).join(
                "эй!",
                "пока"
                ),
                Joiner.on(ln).join(
                        "Мне нечего тебе сказать...",
                        ""
                ));
    }
}