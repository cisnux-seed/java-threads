package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveStreamTest {
    @Test
    void publish() throws InterruptedException {
        try (var publisher = new SubmissionPublisher<String>()) {
            final var subscriber1 = new PrintSubscriber("A", 1000L);
            final var subscriber2 = new PrintSubscriber("B", 500L);
            publisher.subscribe(subscriber1);
            publisher.subscribe(subscriber2);

            final var executor = Executors.newFixedThreadPool(10);
            executor.execute(() -> {
                for (int i = 0; i < 100; i++) {
                    publisher.submit("Fajra-" + i);
                    System.out.println(Thread.currentThread().getName() + " : Send Fajra-" + i);
                }
            });
            Thread.sleep(100 * 1000);
        }
    }

    @Test
    void buffer() throws InterruptedException {
        try (final var publisher = new SubmissionPublisher<String>(Executors.newWorkStealingPool(), 10)) {
            final var subscriber1 = new PrintSubscriber("A", 1000L);
            final var subscriber2 = new PrintSubscriber("B", 500L);
            publisher.subscribe(subscriber1);
            publisher.subscribe(subscriber2);

            var executor = Executors.newFixedThreadPool(10);
            executor.execute(() -> {
                for (int i = 0; i < 100; i++) {
                    publisher.submit("Fajra-" + i);
                    System.out.println(Thread.currentThread().getName() + " : Send Fajra-" + i);
                }
            });

            Thread.sleep(100 * 1000);
        }
    }

    @Test
    void processor() throws InterruptedException {
        try (final var publisher = new SubmissionPublisher<String>()) {
            final var processor = new HelloProcessor();
            publisher.subscribe(processor);

            final var subscriber = new PrintSubscriber("A", 1000L);
            processor.subscribe(subscriber);

            final var executor = Executors.newFixedThreadPool(10);
            executor.execute(() -> {
                for (int i = 0; i < 100; i++) {
                    publisher.submit("Fajra-" + i);
                    System.out.println(Thread.currentThread().getName() + " : Send Fajra-" + i);
                }
            });

            Thread.sleep(100 * 1000);
        }
    }
}
