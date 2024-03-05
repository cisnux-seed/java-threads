package dev.cisnux.javathreads;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class SimpleForkJoinTask extends RecursiveAction {
    private final List<Integer> integers;

    public SimpleForkJoinTask(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    protected void compute() {
        if (integers.size() <= 10) {
            // execution
            doExecute();
        } else {
            // fork
            forkCompute();
        }
    }

    private void doExecute() {
        integers.forEach(integer -> System.out.println(Thread.currentThread().getName() + ":" + integer));
    }

    private void forkCompute() {
        final List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
        final List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());

        final SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);
        final SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);

        ForkJoinTask.invokeAll(task1, task2);
    }
}
