package ru.job4j.multithreading.tasks.elevatorsimulator;

import org.junit.Test;

public class StartTest {

    @Test(expected =  IllegalArgumentException.class)
    public void whenNumOfFloorsLessThan5ThrowsException() throws InterruptedException {
        String floor = "4";
        String[] args = new String[]{"-f", floor, "-h", "3", "-s", "1", "-d", "5"};
        new Start(args, System.in).init();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenNumOfFloorsMoreThan20ThrowsException() throws InterruptedException {
        String floor = "21";
        String[] args = new String[]{"-f", floor, "-h", "3", "-s", "1", "-d", "5"};
        new Start(args, System.in).init();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenFloorsHeightLessThan2ThrowsException() throws InterruptedException {
        String height = "1";
        String[] args = new String[]{"-f", "5", "-h", height, "-s", "1", "-d", "5"};
        new Start(args, System.in).init();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenFloorsHeightMoreThan10ThrowsException() throws InterruptedException {
        String height = "11";
        String[] args = new String[]{"-f", "5", "-h", height, "-s", "1", "-d", "5"};
        new Start(args, System.in).init();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenSpeedLessThan1ThrowsException() throws InterruptedException {
        String speed = "0";
        String[] args = new String[]{"-f", "5", "-h", "3", "-s", speed, "-d", "5"};
        new Start(args, System.in).init();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenSpeedMoreThan10ThrowsException() throws InterruptedException {
        String speed = "11";
        String[] args = new String[]{"-f", "5", "-h", "3", "-s", speed, "-d", "5"};
        new Start(args, System.in).init();
    }


    @Test(expected =  IllegalArgumentException.class)
    public void whenTimeBetweenOpeningAndClosingDoorsLessThan1ThrowsException() throws InterruptedException {
        String doors = "0";
        String[] args = new String[]{"-f", "5", "-h", "3", "-s", "3", "-d", doors};
        new Start(args, System.in).init();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenIncorrectNumOfArgsThrowsException() throws InterruptedException {
        String[] args = new String[]{"-f", "5", "-h", "3", "-s", "3", "-d"};
        new Start(args, System.in).init();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenIncorrectTypeOfKeyArgThrowsException() throws InterruptedException {
        String[] args = new String[]{"-wrong", "5", "-h", "3", "-s", "3", "-d", "5"};
        new Start(args, System.in).init();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenIncorrectTypeOfValueArgThrowsException() throws InterruptedException {
        String[] args = new String[]{"-f", "wrong", "-h", "3", "-s", "3", "-d", "5"};
        new Start(args, System.in).init();
    }
}