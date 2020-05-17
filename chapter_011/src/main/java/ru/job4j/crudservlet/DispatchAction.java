package ru.job4j.crudservlet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Dispatch actions from Servlets to Logic Layout (ValidateService).
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class DispatchAction {
    private static final DispatchAction INSTANCE = new DispatchAction().init();
    private final Map<String, Function<User, String>> dispatch = new HashMap<>();
    private final ValidateService logic = ValidateService.INSTANCE;

    private DispatchAction() {
    }

    public static DispatchAction getInstance() {
        return INSTANCE;
    }

    private DispatchAction init() {
        load("add", toAdd());
        load("update", toUpdate());
        load("delete", toDelete());
        return this;
    }

    private void load(String action, Function<User, String> handle) {
        dispatch.put(action, handle);
    }

    public String toDo(String action, User model) {
        return dispatch.get(action).apply(model);
    }

    private Function<User, String> toAdd() {
        return logic::add;
    }

    private Function<User, String> toUpdate() {
        return logic::update;
    }

    private Function<User, String> toDelete() {
        return logic::delete;
    }

    Collection<User> toFindAll() {
        return logic.findAll();
    }

    Map<String, Collection<String>> toFindLocations() {
        return logic.findLocations();
    }
}