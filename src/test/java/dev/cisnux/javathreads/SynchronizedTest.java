package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

public class SynchronizedTest {
    @Test
    void counter() throws InterruptedException {
        final var counter = new SynchronizedCounter();
        final Runnable runnable = () -> {
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
}
