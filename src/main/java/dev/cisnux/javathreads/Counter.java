package dev.cisnux.javathreads;

public class Counter {
    private int value;


    void increment(){
        this.value++;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
