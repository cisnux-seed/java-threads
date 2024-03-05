package dev.cisnux.javathreads;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriterLock {
    private Long value = 0L;

    final private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment() {
        try {
            lock.writeLock().lock();
            value++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Long getValue() {
        try {
            // the readlock doesn't need wait other to unlock
            lock.readLock().lock();
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }
}
