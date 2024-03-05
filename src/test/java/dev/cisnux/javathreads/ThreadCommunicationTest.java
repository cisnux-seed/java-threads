package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {
    private volatile String message = null;

    @Test
    void manual() throws InterruptedException {
        final var thread1 = new Thread(() -> {
            while (message == null) {
                Thread.onSpinWait();
                // wait
            }
            System.out.println(message);
        });

        final var thread2 = new Thread(() -> {
            message = "Fajra Risqulla";
        });

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();
    }

    @Test
    void waitNotify() throws InterruptedException {
        final var lock = new Object();
        final var thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final var thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Fajra Risqulla";
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    void waitNotifyAll() throws InterruptedException {
        final var lock = new Object();

        final var thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final var thread3 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final var thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Fajra Risqulla";
                lock.notifyAll();
            }
        });

        thread1.start();
        thread3.start();

        thread2.start();

        thread1.join();
        thread3.join();

        thread2.join();
    }
}
