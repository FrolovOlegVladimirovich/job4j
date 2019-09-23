package ru.job4j.scriptlist;

import java.util.*;

/**
 * Задача: задан список скриптов с указанием их зависимостей.
 * Например: 1 - [2, 3], 2 - [4], 3 - [4, 5], 4 - [], 5 - [].
 *
 * Необходимо написать метод, возвращающий список всех скриптов, которые нужны для загрузки входящего скрипта.
 * Например: чтобы выполнить скрипт 1, нужно выполнить скрипт (2, 3), которые в свою очередь зависят от 4 и 5 скрипта.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 23.09.2019
 * @version 1.0
 */
public class ScriptList {

    /**
     * Возвращает список всех скриптов, необходимых для загрузки входящего скрипта.
     * @param scripts Map скриптов и их зависимостей. Key - срипт. Value - скрипты-зависимости.
     * @param scriptID Входящий номер скрипта.
     * @return List<Integer> - Возвращает список всех скриптов, необходимых для загрузки входящего скрипта.
     */
    List<Integer> load(Map<Integer, List<Integer>> scripts, Integer scriptID) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(scriptID);
        while (!queue.isEmpty()) {
            List<Integer> scriptsList = scripts.get(queue.poll());
            queue.addAll(scriptsList);
            result.addAll(scriptsList);
        }
        return result;
    }
}