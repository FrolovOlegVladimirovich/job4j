package ru.job4j.multithreading.nonblockingalgorithm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Non blocking cache.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class ModelCache {
    private final ConcurrentHashMap<Integer, Base> cache;

    public ModelCache() {
        cache = new ConcurrentHashMap<>();
    }

    public void add(Base model) {
        cache.put(model.getId(), model);
    }

    /**
     * The model is updated in the cache based on the results of comparing the version variable inside the model.
     * If the version value inside the cache differs from the value of this variable inside the model,
     * then the model inside the cache has been concurrent changed by another thread.
     * In this case, an OptimisticException is thrown.
     *
     * @param model Changed base model.
     * @throws OptimisticException when model has already been changed into cache.
     */
    public void update(Base model) throws OptimisticException {
        cache.computeIfPresent(model.getId(),
                (id, value) -> {
                    var version = model.getVersion();
                    if (value.getVersion() != version) {
                        throw new OptimisticException("Value has already been changed.");
                    }
                    model.setVersion(++version);
                    return model;
                }
        );
    }

    public void delete(Base model) {
        cache.remove(model.getId(), model);
    }

    public Base getModel(int id) {
        var model = cache.get(id);
        var result = new Base(model.getId(), model.getName());
        result.setVersion(model.getVersion());
        return result;
    }
}