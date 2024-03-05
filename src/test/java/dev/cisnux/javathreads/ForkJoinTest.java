package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

public class ForkJoinTest {
    @Test
    void create() {
        try (final var forkJoinPool1 = ForkJoinPool.commonPool()) {
        }
        try (final var forkJoinPool2 = new ForkJoinPool(5)) {
        }

        try (final var executor1 = Executors.newWorkStealingPool()) {
        }
        try (final var executor2 = Executors.newWorkStealingPool(5)) {
        }
    }

    @Test
    void recursiveAction() {
        try (final var pool = ForkJoinPool.commonPool()) {
            final var integers = IntStream.range(0, 1000).boxed().toList();

            var task = new SimpleForkJoinTask(integers);
            pool.execute(task);
        }
    }

    @Test
    void recursiveTask() throws ExecutionException, InterruptedException {
        final var integers = IntStream.range(0, 1000).boxed().toList();
        try (final var pool = ForkJoinPool.commonPool()) {

            var task = new TotalRecursiveTask(integers);
            final ForkJoinTask<Long> submit = pool.submit(task);

            final var aLong = submit.get();
            System.out.println(aLong);

            final long sum = integers.stream().mapToLong(value -> value).sum();
            System.out.println(sum);
        }
    }
}
