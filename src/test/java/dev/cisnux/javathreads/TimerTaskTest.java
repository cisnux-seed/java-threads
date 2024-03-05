package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {
    @Test
    void delayedJob() throws InterruptedException {
        final var task = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Delayed Job");
            }
        };

        final var timer = new Timer();
        timer.schedule(task, 2000L);

        Thread.sleep(3000L);
    }

    @Test
    void periodicJob() throws InterruptedException {
        final var task = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Delayed Job");
            }
        };

        final var timer = new Timer();
        timer.schedule(task, 1000L, 2000L);

        Thread.sleep(10_000L);
    }
}
