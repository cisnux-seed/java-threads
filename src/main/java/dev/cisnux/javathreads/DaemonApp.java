package dev.cisnux.javathreads;

public class DaemonApp {
    public static void main(String[] args) {
        final var thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("Run Thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // not waiting
        thread.setDaemon(true);
        thread.start();
    }
}
