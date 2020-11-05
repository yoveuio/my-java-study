package org.example.homework.work7;

import java.util.concurrent.*;

/**
 * @ClassName ForkWork
 * @Description 使用Fork/Join框架实现9000000的计算
 * @Author yoveuio
 * @Date 2020/11/5 10:59
 * @Version 1.0
 */
public class ForkWork extends RecursiveTask<Double> {

    private static final int THRESHOLD = 1000000;
    private int start;
    private int end;
    private double[] list;

    public ForkWork(int start, int end, double[] list) {
        this.start = start;
        this.end = end;
        this.list = list;
    }

    public static Double parallelSum(double[] list) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkWork forkWork = new ForkWork(0, list.length-1, list);
        Future<Double> submit = forkJoinPool.submit(forkWork);
        return submit.get();
    }

    @Override
    protected Double compute() {
        double sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i=start; i<=end; i++) {
                sum += list[i];
            }
        }
        else {
            int middle = (end - start) >> 1;
            ForkWork leftFork = new ForkWork(start, middle, list);
            ForkWork rightFork = new ForkWork(middle+1, end, list);

            //执行子任务
            leftFork.fork();
            rightFork.fork();

            //等待执行完
            Double leftResult = leftFork.join();
            Double rightResult = rightFork.join();

            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        double[] doubles = new double[900000];
        for (int i=0; i<doubles.length-1; ++i) {
            doubles[i] = i+1;
        }
        System.out.println(parallelSum(doubles));
    }
}
