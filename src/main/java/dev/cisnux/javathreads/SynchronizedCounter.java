package dev.cisnux.javathreads;

public class SynchronizedCounter {
    private int value;

    // synchronized method
//    public synchronized void increment() {
//        value++;
//    }

    // synchronized statement
    // must have an object argument
    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public int getValue() {
        return value;
    }
}
