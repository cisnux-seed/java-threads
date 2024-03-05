package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {
    private final Random random = new Random();

    @Test
    void testByPoll() throws InterruptedException {
        try (var executor = Executors.newFixedThreadPool(10)) {
            var completionService = new ExecutorCompletionService<String>(executor);

            // submit task
            try (final var publisher = Executors.newSingleThreadExecutor()) {
                publisher.execute(() -> {
                    for (int i = 0; i < 100; i++) {
                        final var index = i;
                        completionService.submit(() -> {
                            Thread.sleep(random.nextInt(2000));
                            return "Task-" + index;
                        });
                    }
                });
            }

            // poll task
            try (final var subscriber = Executors.newSingleThreadExecutor()) {
                subscriber.execute(() -> {
                    while (true) {
                        try {
                            Future<String> future = completionService.poll(5, TimeUnit.SECONDS);
                            if (future == null) {
                                break;
                            } else {
                                System.out.println(future.get());
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                });
            }
        }
    }

    @Test
    void testByTake() throws InterruptedException {
        try (var executor = Executors.newFixedThreadPool(10)) {
            var completionService = new ExecutorCompletionService<String>(executor);

            // submit task
            try (final var publisher = Executors.newSingleThreadExecutor()) {
                publisher.execute(() -> {
                    for (int i = 0; i < 100; i++) {
                        final var index = i;
                        completionService.submit(() -> {
                            Thread.sleep(random.nextInt(2000));
                            return "Task-" + index;
                        });
                    }
                });
            }

            // poll task
            try (final var subscriber = Executors.newSingleThreadExecutor()) {
                subscriber.execute(() -> {
                    while (true) {
                        try {
                            // waiting if none are yet present
                            Future<String> future = completionService.take();
                            if (future == null) {
                                break;
                            } else {
                                System.out.println(future.get());
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                });
            }
        }
    }
}
