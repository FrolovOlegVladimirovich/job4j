package ru.job4j.multithreading.nonblockingalgorithm;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}