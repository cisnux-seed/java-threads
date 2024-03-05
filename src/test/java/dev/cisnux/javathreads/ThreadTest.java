package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void mainThread() {
        final var name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createThread() {
        final Runnable runnable = () -> {
            System.out.println("Hello from thread name : " + Thread.currentThread().getName());
            System.out.println("Hello from thread id : " + Thread.currentThread().threadId());
        };

        final var thread = new Thread(runnable);
        thread.start();

        System.out.println("program is completed");
    }

    @Test
    void threadSleep() {
        final Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        final var thread = new Thread(runnable);
        thread.start();
    }

    @Test
    void threadJoin() throws InterruptedException {
        final Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        final var thread = new Thread(runnable);
        thread.start();
        System.out.println("Waiting....");
        thread.join();
        System.out.println("Program is Completed");
    }

    @Test
    void threadInterrupt() throws InterruptedException {
        final Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable" + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        final var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        // after 5 seconds interrupt the runnable
        thread.interrupt();
        System.out.println("Waiting....");
        thread.join();
        System.out.println("Program is Completed");
    }

    @Test
    void correctThreadInterrupt() throws InterruptedException {
        final Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable" + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };

        final var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        // after 5 seconds interrupt the runnable
        thread.interrupt();
        System.out.println("Waiting....");
        thread.join();
        System.out.println("Program is Completed");
    }

    @Test
    void otherCorrectThreadInterrupt() throws InterruptedException {
        final Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted())
                    return;
                System.out.println("Runnable" + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };

        final var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        // after 5 seconds interrupt the runnable
        thread.interrupt();
        System.out.println("Waiting....");
        thread.join();
        System.out.println("Program is Completed");
    }

    @Test
    void threadName() {
        var thread = new Thread(() -> {
            System.out.println("Run in thread : " + Thread.currentThread().getName());
        });
        thread.setName("Fajra");
        thread.start();
    }

    @Test
    void threadState() throws InterruptedException {
        var thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Run in thread : " + Thread.currentThread().getName());
        });
        thread.setName("Fajra");
        System.out.println(thread.getState());

        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}
