package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {
    @Test
    void testFuture() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newSingleThreadExecutor()) {

            Callable<String> callable = () -> {
                Thread.sleep(5000);
                return "hi";
            };

            final var future = executor.submit(callable);
            System.out.println("Future is completed");

            while (!future.isDone()) {
                System.out.println("Waiting future");
                Thread.sleep(1000);
            }

            String value = future.get();
            System.out.println(value);
        }
    }

    @Test
    void testFutureCancel() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newSingleThreadExecutor()) {

            Callable<String> callable = () -> {
                Thread.sleep(5000);
                return "hi";
            };

            final var future = executor.submit(callable);
            System.out.println("Future is completed");

            Thread.sleep(2000);
            future.cancel(true);

            System.out.println(future.isCancelled());
            String value = future.get();
            System.out.println(value);
        }
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        try (var executor = Executors.newFixedThreadPool(10)) {

            final var callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
                Thread.sleep(value * 500L);
                return String.valueOf(value);
            }).toList();

            final var futures = executor.invokeAll(callables);

            for (Future<String> stringFuture : futures) {
                System.out.println(stringFuture.get());
            }
        }
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        try (var executor = Executors.newFixedThreadPool(10)) {
            final var callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
                Thread.sleep(value * 500L);
                return String.valueOf(value);
            }).toList();

            final var fastestCallable = executor.invokeAny(callables);
            System.out.println(fastestCallable);
        }
    }
}
