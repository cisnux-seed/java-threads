package dev.cisnux.javathreads;

import java.util.concurrent.atomic.AtomicLong;

public class CounterAtomic {
    private final AtomicLong value = new AtomicLong(0L);

    public void increment() {
        value.incrementAndGet();
    }

    public long getValue() {
        return value.get();
    }
}
