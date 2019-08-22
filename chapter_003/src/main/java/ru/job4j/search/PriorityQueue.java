package ru.job4j.search;

import java.util.LinkedList;

/**
 * Список задач с приоритетом на LinkedList.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.08.2019
 * @version 1.0
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Добавляет задачу в список, определяя позицию по индексу поля priority.
     * Чем меньше индекс priority, тем выше приоритет задачи.
     * Задачи с наименьшим индексом (наибольшим приоритетом) добавляются в начало списка.
     * @param task Задача.
     */
    public void put(Task task) {
        if (tasks.size() != 0) {
            for (var i = 0; i != tasks.size(); i++) {
                if (task.getPriority() <= tasks.get(i).getPriority()) {
                    tasks.add(i, task);
                    break;
                } else if (task.getPriority() >= tasks.peekLast().getPriority()) {
                    tasks.addLast(task);
                    break;
                }
            }
        } else {
            tasks.add(0, task);
        }
    }

    /**
     * Получает первый элемент из списка задач,
     * т.е. задачу с наивысшим приоритетом.
     * И удаляет данную задачу из списка.
     */
    public Task take() {
        return tasks.poll();
    }
}
