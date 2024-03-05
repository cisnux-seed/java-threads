package dev.cisnux.javathreads;

public class ThreadSleepApp {
    public static void main(String[] args) {
        final Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello from thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        final var thread = new Thread(runnable);
        thread.start();
    }
}
