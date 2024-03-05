package dev.cisnux.javathreads;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskApp {
    public static void main(String[] args) {
        final var task = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Delayed Job");
            }
        };

        final var timer = new Timer();
        timer.schedule(task, 2000L);
    }
}
