package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;

public class ExchangerTest {
    @Test
    void test() throws InterruptedException {
        final var exchanger = new Exchanger<String>();

        try (final var executor = Executors.newFixedThreadPool(10)) {
            executor.execute(() -> {
                try {
                    System.out.println("Thread 1 : Send : First");
                    Thread.sleep(1000);
                    final var result = exchanger.exchange("First");
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("Thread 1 : Receive : " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            executor.execute(() -> {
                try {
                    System.out.println("Thread 2 : Send : Second");
                    Thread.sleep(2000);
                    final var result = exchanger.exchange("Second");
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("Thread 2 : Receive : " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
