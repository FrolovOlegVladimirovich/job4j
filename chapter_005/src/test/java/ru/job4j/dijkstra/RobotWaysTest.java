package ru.job4j.dijkstra;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тест RobotWays.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 05.10.2019
 * @version 1.0
 */
public class RobotWaysTest {
    @Test
    public void resultWayIs11115EnergyIs4() {
        int[][] board = new int[][]{
                {1, 2, 3},
                {1, 3, 6},
                {1, 1, 5}
        };
        RobotWays robotWays = new RobotWays(board, 0, 0, 2, 2);
        List<Integer> resultWay = robotWays.optimalWay();
        int resultEnergy = robotWays.optimalEnergy(resultWay);

        assertThat(resultWay, is(List.of(1, 1, 1, 1, 5)));
        assertThat(resultEnergy, is(4));
    }

    @Test
    public void resultWayIs12652EnergyIs9() {
        int[][] board = new int[][]{
                {1, 2, 6},
                {8, 10, 5},
                {7, 5, 2}
        };
        RobotWays robotWays = new RobotWays(board, 0, 0, 2, 2);
        List<Integer> resultWay = robotWays.optimalWay();
        int resultEnergy = robotWays.optimalEnergy(resultWay);

        assertThat(resultWay, is(List.of(1, 2, 6, 5, 2)));
        assertThat(resultEnergy, is(9));
    }

    @Test
    public void resultWayIs11365EnergyIs6() {
        int[][] board = new int[][]{
                {1, 2, 4},
                {1, 3, 6},
                {1, 9, 5}
        };
        RobotWays robotWays = new RobotWays(board, 0, 0, 2, 2);
        List<Integer> resultWay = robotWays.optimalWay();
        int resultEnergy = robotWays.optimalEnergy(resultWay);

        assertThat(resultWay, is(List.of(1, 1, 3, 6, 5)));
        assertThat(resultEnergy, is(6));
    }

    @Test
    public void resultWayIs198735EnergyIs14() {
        int[][] board = new int[][]{
                {1, 9, 4},
                {10, 8, 7},
                {5, 1, 3}
        };
        RobotWays robotWays = new RobotWays(board, 0, 0, 2, 2);
        List<Integer> resultWay = robotWays.optimalWay();
        int resultEnergy = robotWays.optimalEnergy(resultWay);

        assertThat(resultWay, is(List.of(1, 9, 8, 7, 3)));
        assertThat(resultEnergy, is(14));
    }

    @Test
    public void resultWayIs111111EnergyIs0() {
        int[][] board = new int[][]{
                {2, 2, 2, 2, 2},
                {2, 1, 2, 2, 2},
                {2, 1, 1, 1, 2},
                {2, 1, 2, 1, 2},
                {2, 2, 2, 1, 1}
        };
        RobotWays robotWays = new RobotWays(board, 1, 1, 4, 3);
        List<Integer> resultWay = robotWays.optimalWay();
        int resultEnergy = robotWays.optimalEnergy(resultWay);

        assertThat(resultWay, is(List.of(1, 1, 1, 1, 1, 1)));
        assertThat(resultEnergy, is(0));
    }

    @Test
    public void resultWayIs11111EnergyIs0() {
        int[][] board = new int[][]{
                {1, 2, 2},
                {1, 1, 1},
                {1, 2, 1}
        };
        RobotWays robotWays = new RobotWays(board, 0, 0, 2, 2);
        List<Integer> resultWay = robotWays.optimalWay();
        int resultEnergy = robotWays.optimalEnergy(resultWay);

        assertThat(resultWay, is(List.of(1, 1, 1, 1, 1)));
        assertThat(resultEnergy, is(0));
    }

    @Test
    public void resultWayIs1111111EnergyIs0() {
        int[][] board = new int[][]{
                {2, 2, 1, 2, 1},
                {2, 1, 1, 1, 1},
                {2, 2, 1, 2, 2},
                {2, 1, 1, 1, 2},
                {2, 2, 2, 2, 2}
        };
        RobotWays robotWays = new RobotWays(board, 3, 1, 0, 4);
        List<Integer> resultWay = robotWays.optimalWay();
        int resultEnergy = robotWays.optimalEnergy(resultWay);

        assertThat(resultWay, is(List.of(1, 1, 1, 1, 1, 1, 1)));
        assertThat(resultEnergy, is(0));
    }

    @Test
    public void resultWayIs11111111112EnergyIs1() {
        int[][] board = new int[][]{
                {2, 2, 2, 1, 2},
                {2, 2, 2, 1, 2},
                {1, 2, 1, 1, 2},
                {1, 2, 1, 2, 2},
                {1, 1, 1, 2, 2}
        };
        RobotWays robotWays = new RobotWays(board, 2, 0, 0, 4);
        List<Integer> resultWay = robotWays.optimalWay();
        int resultEnergy = robotWays.optimalEnergy(resultWay);

        assertThat(resultWay, is(List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2)));
        assertThat(resultEnergy, is(1));
    }
}