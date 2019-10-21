package ru.job4j.io.socket.wiseoracle;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест клиентской стороны чат-бота через сокет.
 *
 * В тесте используются библиотеки Mockito и Guava.
 */
public class ClientTest {
    private final String ln = System.lineSeparator();

    private void testClient(String output, String expect) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(Joiner.on(ln).join(
                "Здравствуй, друг! Я Мудрый Оракул.",
                "Чудесно! Как твои?",
                "Что случилось, друг мой?",
                "Рад за тебя, друг мой!",
                "Прекрасно, друг мой!",
                ""
        ).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        Scanner console = new Scanner(output);
        client.start(console);

        assertThat(out.toString(), is(expect));
    }

    @Test
    public void whenAskExitResultIsExitProgram() throws IOException {
        testClient(Joiner.on(ln).join(
                "Олег",
                "привет",
                "как дела?",
                "плохо",
                "отлично",
                "хорошо",
                "пока"
                ),
                Joiner.on(ln).join(
                        "привет",
                        "как дела?",
                        "плохо",
                        "отлично",
                        "хорошо",
                        "пока",
                        ""
                ));
    }
}