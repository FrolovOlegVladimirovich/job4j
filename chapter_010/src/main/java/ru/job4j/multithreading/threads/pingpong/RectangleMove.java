package ru.job4j.multithreading.threads.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int way = 1;
        while (true) {
            if (this.rect.getX() == 290) {
                way = -1;
            }
            if (this.rect.getX() == 0) {
                way = 1;
            }
            this.rect.setX(this.rect.getX() + way);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}