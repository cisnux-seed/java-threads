package dev.cisnux.javathreads;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PeriodicJobApp {
    public static void main(String[] args) throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(10);
        var future = executor.scheduleAtFixedRate(() -> System.out.println("Hello Scheduled"), 2, 2, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.MILLISECONDS));

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
