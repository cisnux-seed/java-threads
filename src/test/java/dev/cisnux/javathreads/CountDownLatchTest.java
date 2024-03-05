package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    @Test
    void test() {
        final var countDownLatch = new CountDownLatch(5);
        try (final var executor = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 5; i++) {
                executor.execute(() -> {
                    try {
                        System.out.println("Start Task");
                        Thread.sleep(2000);
                        System.out.println("Finish Task");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                        System.out.println(countDownLatch.getCount());
                    }
                });
            }

            executor.execute(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("Finish All Tasks");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
