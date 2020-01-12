package ru.job4j.multithreading.nonblockingalgorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

public class ModelCacheTest {

    @Test
    public void whenThreadsConcurrentChangeModelNameResultIsException() throws InterruptedException {
        ModelCache cache = new ModelCache();
        Base oldBase = new Base(1, "old");
        cache.add(oldBase);
        AtomicReference<Exception> ex = new AtomicReference<>();

        Thread thread1 = new Thread(
                () -> {
                    try {
                        var model = cache.getModel(1);
                        model.changeName("new_1");
                        Thread.sleep(100);
                        cache.update(model);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    try {
                        var model = cache.getModel(1);
                        model.changeName("new_2");
                        Thread.sleep(100);
                        cache.update(model);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertThat(ex.get().getMessage(), is("Value has already been changed."));
    }
}