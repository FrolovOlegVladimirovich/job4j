package ru.job4j.generic;

import java.util.Iterator;
import java.util.Objects;

/**
 * Реализация контейнеров для хранения объектов с ограничением типов.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 03.09.2019
 * @version 1.0
 */
public class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store;

    public AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    @Override
    public void add(Base model) {
        this.store.add((T) model);
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        int indexElement = 0;
        Iterator it = store.iterator();
        while (it.hasNext()) {
            Base element = (Base) it.next();
            if (element.getId().equals(id)) {
                store.set(indexElement, (T) model);
                result = true;
                break;
            } else {
                indexElement++;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int indexElement = 0;
        Iterator it = store.iterator();
        while (it.hasNext()) {
            Base element = (Base) it.next();
            if (element.getId().equals(id)) {
                store.remove(indexElement);
                result = true;
                break;
            } else {
                indexElement++;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        Iterator it = store.iterator();
        while (it.hasNext()) {
            Base element = (Base) it.next();
            if (element.getId().equals(id)) {
                result = (T) element;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractStore<?> that = (AbstractStore<?>) o;
        return Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store);
    }
}