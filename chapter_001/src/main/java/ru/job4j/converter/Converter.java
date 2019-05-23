package ru.job4j.converter;

/**
 * Конвертер валюты.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 09.05.2019
 * @version 1.0
 */
public class Converter {

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро (курс евро 70 руб).
     */
    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары (курс доллара 60 руб).
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return Рубли (курс евро 70 руб).
     */
    public int euroToRuble(int value) {
        return value * 70;
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return Рубли (курс доллара 60 руб).
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }
}