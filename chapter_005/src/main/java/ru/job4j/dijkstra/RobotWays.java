package ru.job4j.dijkstra;

import java.util.*;

/**
 * Класс, реализующий расчет оптимального пути робота по массиву int.
 * Использует алгоритм Дейкстры.
 *
 *
 * Задача:
 *
 * Задан двумерный массив. Массив заполнен числами. По массиву двигается робот.
 * Робот может двигаться вниз, вверх и вправо. Задана начальная и конечная точка.
 * Перемещение из одной клетки в другую затрачивает энергию.
 * Энергия рассчитывается как разность значений клеток по модулю.
 * Например, ход из клетки 1 в 10 будет оцениваться в 9 единиц.
 * Необходимо написать метод, который определяет наименее трудозатратный путь.
 *
 *
 * Поля:
 *
 * board - начальный массив значений int.
 *
 * cells - массив ячеек Cells, сформированный из начального массива board;
 * массив, ячейки и их содержимое сформированы согласно логике задачи.
 *
 * finishCell - ячейка, в которую идет робот.
 *
 * cellsList - массив ячеек cells в виде списка. Необходим для работы алгоритма Дейкстры.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.10.2019
 * @version 1.0
 */
public class RobotWays {
    private final int[][]board;
    private final Cell[][] cells;
    private Cell finishCell;
    private final HashSet<Cell> cellsList;

    /**
     * Конструктор инициализирует все поля класса,
     * также заполняет:
     * массив ячеек cells;
     * список ячеек ways, в которые может пойти робот из каждой ячейки в cells;
     * список ячеек cellsList.
     * Назначает стартовой ячейке cost == 0;
     *
     * @param board - начальный массив значений int.
     * @param sx - координата начальной ячейки по оси X.
     * @param sy - координата начальной ячейки по оси Y.
     * @param fx - координата конечной ячейки по оси X.
     * @param fy - координата конечной ячейки по оси Y.
     */
    public RobotWays(int[][] board, int sx, int sy, int fx, int fy) {
        this.board = board;
        cells = new Cell[board.length][board.length];
        fillCells();
        fillWays();
        finishCell = cells[fx][fy];
        cells[sx][sy].setCost(0);
        cellsList = new HashSet<>();
        fillCellsList(cellsList);
    }

    /**
     * Заполняет массив cell ячейками Cell,
     * используя данные из массива board.
     */
    private void fillCells() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Cell newCell = new Cell(i, j, board[i][j]);
                cells[i][j] = newCell;
            }
        }
    }

    /**
     * Заполняет поле ways (пути до соседних ячеек)
     * каждой ячейки массива cells по логике задачи:
     * робот может двигаться вниз, вверх и вправо.
     */
    private void fillWays() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                List<Cell> ways = new ArrayList<>();
                if (j < cells.length - 1) {
                    ways.add(cells[i][j + 1]);
                }
                if (i < cells.length - 1) {
                    ways.add(cells[i + 1][j]);
                }
                if (i != 0) {
                    ways.add(cells[i - 1][j]);
                }
                cells[i][j].setWays(ways);
            }
        }
    }

    /**
     * Заполняет список ячеек cellsList.
     * @param cellsList - ячейки cells в виде списка.
     */
    private void fillCellsList(HashSet<Cell> cellsList) {
        for (Cell[] cell : cells) {
            cellsList.addAll(Arrays.asList(cell));
        }
    }

    /**
     * Реализация алгоритма Дейкстры для поиска наиболее энергоэффективного пути.
     *
     * Данный метод назначает каждой ячейке Cell в поле parent - родителя ячейки,
     * из которой пришел робот, в поле cost - количество энергии,
     * потраченной на передвижение из ячейки parent в текущую ячейку.
     *
     * В результате работы алгоритма через поле parent возможно найти
     * минимально-трудозатратный путь из одной ячейки в любую другую.
     *
     * @param cellsList - ячейки cells в виде списка.
     */
    private void dijkstraAlgorithm(HashSet<Cell> cellsList) {
        while (!cellsList.isEmpty()) {
            Cell node = cellsList.stream()
                    .min(Comparator.comparingInt(Cell::getCost))
                    .get();
            if (node.getCost() == Integer.MAX_VALUE) {
                break;
            }
            cellsList.remove(node);
            List<Cell> ways = node.getWays();
            int i = 0;
            while (i < ways.size()) {
                Cell way = ways.get(i);
                int sum = node.getCost() + Math.abs(way.getValue() - node.getValue());
                if (sum < way.getCost()) {
                    way.setCost(sum);
                    way.setParent(node);
                }
                i++;
            }
        }
    }

    /**
     * Рассчитывает минимальный по затратам энергии путь
     * из начальной ячейки в конечную.
     *
     * @return Список ячеек Cell из начальной в конечную.
     */
    private List<Cell> optimalCellsWay() {
        Deque<Cell> cellDeque = new LinkedList<>();
        dijkstraAlgorithm(cellsList);
        cellDeque.add(finishCell);
        while (finishCell != null) {
            cellDeque.add(this.finishCell.getParent());
            this.finishCell = this.finishCell.getParent();
        }
        ArrayList<Cell> resultCellsWay = new ArrayList<>();
        while (!cellDeque.isEmpty()) {
            resultCellsWay.add(cellDeque.pollLast());
        }
        resultCellsWay.remove(null);
        return resultCellsWay;
    }

    /**
     * Возвращает оптимальный путь робота в виде значений ячеек:
     * от стартовой до конечной.
     *
     * @return Список значений ячеек Cell - оптимальный путь робота от стартовой
     * до конечной ячейки.
     */
    public List<Integer> optimalWay() {
        ArrayList<Integer> resultValuesWay = new ArrayList<>();
        List<Cell> cellsWay = optimalCellsWay();
        for (Cell cell : cellsWay) {
            resultValuesWay.add(cell.getValue());
        }
        return resultValuesWay;
    }

    /**
     * Количество потраченной роботом энергии
     * на пути от стартовой ячейки до конечной.
     *
     * @param list - список значений оптимального пути робота
     * от стартовой до конечной ячейки.*
     * @return Количество потраченной роботом энергии.
     */
    public int optimalEnergy(List<Integer> list) {
        int result = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            int delta = Math.abs(list.get(i) - list.get(i + 1));
            result += delta;
        }
        return result;
    }
}