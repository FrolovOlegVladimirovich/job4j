package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест списка задач с приоритетом.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 02.08.2019
 * @version 1.0
 */
public class PriorityQueueTest {

    /**
     * Тест на получение задачи с наивысшим приоритетом.
     */
    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}
