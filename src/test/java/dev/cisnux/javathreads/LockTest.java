package dev.cisnux.javathreads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    @Test
    void testLock() throws InterruptedException {
        final var counter = new CounterLock();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        final var thread1 = new Thread(runnable);
        final var thread2 = new Thread(runnable);
        final var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }

    @Test
    void testReadWriteLock() throws InterruptedException {
        final var counter = new CounterReadWriterLock();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        final var thread1 = new Thread(runnable);
        final var thread2 = new Thread(runnable);
        final var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }

    String message;

    @Test
    void condition() throws InterruptedException {
        final var lock = new ReentrantLock();
        final var condition = lock.newCondition();

        final var thread1 = new Thread(() -> {
            try {
                lock.lock();
                condition.await();
                System.out.println(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        final var thread3 = new Thread(() -> {
            try {
                lock.lock();
                condition.await();
                System.out.println(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        final var thread2 = new Thread(() -> {
            try {
                lock.lock();
                Thread.sleep(2000);
                message = "Fajra Risqulla";
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
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
