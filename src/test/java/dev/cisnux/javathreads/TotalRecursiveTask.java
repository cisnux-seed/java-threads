package dev.cisnux.javathreads;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class TotalRecursiveTask extends RecursiveTask<Long> {
    private final List<Integer> integers;

    public TotalRecursiveTask(List<Integer> integers) {
        this.integers = integers;
    }


    @Override
    protected Long compute() {
        if (integers.size() <= 10) {
            return doCompute();
        }
        return forkCompute();
    }

    private Long forkCompute() {
        final var integers1 = this.integers.subList(0, this.integers.size() / 2);
        final var integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());

        final var task1 = new TotalRecursiveTask(integers1);
        final var task2 = new TotalRecursiveTask(integers2);

        ForkJoinTask.invokeAll(task1, task2);

        return task1.join() + task2.join();
    }

    private Long doCompute() {
        return integers.stream().mapToLong(value -> value).peek(value -> {
            System.out.println(Thread.currentThread().getName() + " : " + value);
        }).sum();
    }
}
