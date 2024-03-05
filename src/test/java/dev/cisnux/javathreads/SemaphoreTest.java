package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    @Test
    void test() {
        final var semaphore = new Semaphore(10);
        try (final var executor = Executors.newFixedThreadPool(100)) {
            for (int i = 0; i < 100; i++) {
                executor.execute(() -> {
                    try {
                        semaphore.acquire();
                        Thread.sleep(1000L);
                        System.out.println("Finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                });
            }
        }
    }
}
