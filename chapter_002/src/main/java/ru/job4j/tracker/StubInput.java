package ru.job4j.tracker;

import java.util.List;

/**
 * Используется для получения заранее заданных значений ввода в консоль.
 * С целью последующего использования значений в тестах.
 */
public class StubInput implements Input {
    private final List<String> value;
    private int position;

    /**
     * Ввод пользовательских данных из масиива типа String.
     *
     * @param value - массив с данными в формате String для последующего ввода в консоль тестом.
     */
    public StubInput(final List<String> value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value.get(this.position++);
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.value.get(this.position++));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Выход из диапазона заданных значений!");
        }
        return key;
    }
}
