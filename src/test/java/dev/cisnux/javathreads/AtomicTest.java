package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class AtomicTest {
    @Test
    void counter() throws InterruptedException {
        final var counter = new CounterAtomic();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        final var thread1 = new Thread(runnable);
        final var thread2 = new Thread(runnable);
        final var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }

    @Test
    void calculateArea() throws InterruptedException {
        final var calculator = new Calculator(new Rect(20 + new Random().nextDouble(100), 30 + new Random().nextDouble(200)));
        Runnable runnable = () -> {
            try {
                Thread.sleep(1_000L);
                calculator.calculateArea();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        final var thread1 = new Thread(runnable);

        thread1.start();
        thread1.join();

        System.out.println(calculator.getArea());
    }

}
