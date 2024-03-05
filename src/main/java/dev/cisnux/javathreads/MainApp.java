package dev.cisnux.javathreads;

public class MainApp {
    public static void main(String[] args) {
        final var name = Thread.currentThread().getName();
        System.out.println(name);
    }
}
