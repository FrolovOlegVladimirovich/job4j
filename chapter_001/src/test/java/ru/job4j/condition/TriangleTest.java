package ru.job4j.condition;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;


public class TriangleTest {

    @Test
    /**
     * Тест: вычисление площади треугольника.
     */
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point one = new Point(0, 0);
        Point two = new Point(0, 2);
        Point three = new Point(2, 0);
        Triangle triangle = new Triangle(one, two, three);
        double result = triangle.area();
        double expected = 2D;
        assertThat(result, closeTo(expected, 0.1));
    }
}
