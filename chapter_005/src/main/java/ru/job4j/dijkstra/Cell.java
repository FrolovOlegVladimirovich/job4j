package ru.job4j.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ячейка - Node.
 *
 * Местоположение ячейки в массиве:
 * x - координата по оси X;
 * y - координата по оси Y.
 *
 * value - значение ячейки.
 * parent - ячейка-родитель, которая ссылается на текущую ячейку.
 * cost - цена пути из ячейки parent в текущую ячейку.
 * ways - список ячеек, путь в которые возможен из текущей ячейки.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.10.2019
 * @version 1.0
 */
public class Cell {
    private final int x;
    private final int y;
    private final int value;
    private Cell parent;
    private int cost;
    private List<Cell> ways;

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        cost = Integer.MAX_VALUE;
        parent = null;
        ways = new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public Cell getParent() {
        return parent;
    }

    public List<Cell> getWays() {
        return ways;
    }

    public void setWays(List<Cell> ways) {
        this.ways = ways;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x
                && y == cell.y
                && value == cell.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, value);
    }
}