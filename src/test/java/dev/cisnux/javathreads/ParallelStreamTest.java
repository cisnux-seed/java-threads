package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class ParallelStreamTest {
    @Test
    void parallel() {
        final var stream = IntStream.range(0, 1000).boxed();

        stream.parallel().forEach(integer -> {
            System.out.println(Thread.currentThread().getName() + " : " + integer);
        });
    }

    @Test
    void customPool() {
        try (var pool = new ForkJoinPool(1)) {
            final var task = pool.submit(() -> {
                final var stream = IntStream.range(0, 1000).boxed();
                stream.parallel().forEach(integer -> {
                    System.out.println(Thread.currentThread().getName() + " : " + integer);
                });
            });
            task.join();
        }
    }
}
