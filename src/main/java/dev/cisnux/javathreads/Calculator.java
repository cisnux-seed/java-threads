package dev.cisnux.javathreads;

import java.util.concurrent.atomic.AtomicReference;

public class Calculator {
    public Calculator(Rect rect) {
        this.rect = new AtomicReference<>(rect);
    }

    private final AtomicReference<Rect> rect;


    public void calculateArea() {
        rect.updateAndGet(rectangle -> rectangle.copy().setMutableArea(rectangle.width() * rectangle.height()).build());
    }

    public double getArea() {
        return rect.get().area();
    }
}
