package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    @Test
    void test() {
        final var cyclicBarrier = new CyclicBarrier(5);
        try (var executor = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 5; i++) {
                executor.execute(() -> {
                    try {
                        System.out.println("Waiting");
                        cyclicBarrier.await();
                        System.out.println("Done Waiting");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}
