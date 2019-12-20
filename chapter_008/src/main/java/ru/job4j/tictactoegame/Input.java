package ru.job4j.tictactoegame;

/**
 * Interface for sending messages to the user and receiving replies.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface Input {

    /**
     * Passes the request/question to the user and receives a replies.
     * @param request to the user.
     * @return User response.
     */
    String ask(String request);
}