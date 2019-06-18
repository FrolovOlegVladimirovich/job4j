package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест ValidateInput.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 18.06.2019
 * @version 1.0
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    /**
     * Заменяем стандартный вывод на вывод в память для тестирования.
     */
    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    /**
     * Возвращаем обратно стандартный вывод в консоль.
     */
    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    /**
     * Проверка некорректного ввода данных в консоль: ввод букв/иных символов, вместо номера меню.
     */
    @Test
    public void whenInputTextThenNumberFormatException() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"invalid", "1"}));
        input.ask("Enter", new int[] {1});
        assertThat(this.mem.toString(), is("Ошибка! Введите номер меню в правильном формате: \n"));
    }

    /**
     * Проверка некорректного ввода данных в консоль: ввод номера меню вне диапазона.
     */
    @Test
    public void whenInputOutOfRangeNumberThenMenuOutException() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"2", "1"}));
        input.ask("Enter", new int[] {1});
        assertThat(this.mem.toString(), is("Данный пункт меню отсутствует! Введите корректный пункт меню: \n"));
    }
}
