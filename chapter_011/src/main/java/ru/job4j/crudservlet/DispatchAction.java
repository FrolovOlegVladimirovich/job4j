package ru.job4j.crudservlet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Dispatch actions from Servlets to Logic Layout (ValidateService).
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class DispatchAction {
    private final Map<String, Function<User, String>> dispatch = new HashMap<>();
    private final ValidateService logic = ValidateService.INSTANCE;

    public DispatchAction init() {
        load("add", toAdd());
        load("update", toUpdate());
        load("delete", toDelete());
        return this;
    }

    public void load(String action, Function<User, String> handle) {
        dispatch.put(action, handle);
    }

    public String toDo(String action, User model) {
        return dispatch.get(action).apply(model);
    }

    public Function<User, String> toAdd() {
        return logic::add;
    }

    public Function<User, String> toUpdate() {
        return logic::update;
    }

    public Function<User, String> toDelete() {
        return logic::delete;
    }

    public String toFindAll() {
        return logic.findAll();
    }
}