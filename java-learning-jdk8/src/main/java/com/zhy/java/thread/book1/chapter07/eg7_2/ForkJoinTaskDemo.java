package com.zhy.java.thread.book1.chapter07.eg7_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class ForkJoinTaskDemo {
    public static void main(String[] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int start = 1, end = 5;
        CountTask task = new CountTask(start, end);
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(String.format("%s-%s最终相加结果为：%s", start, end, result.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        start = 1;
        end = 100;
        try {
            System.out.println(String.format("%s-%s最终相加结果为：%s", start, end, forkJoinPool.submit(new CountTask(start, end)).get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CountTask extends RecursiveTask<Integer>{
    private static int splitSize = 2;
    private int start, end;
    public CountTask(int start, int end){
        this.start = start;
        this.end = end;
    }
    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= splitSize;
        if(canCompute){
            for (int i=start; i<=end; i++){
                sum = sum + i;
            }
        }else{
            int middle = (start + end) / 2;
            CountTask firstTask = new CountTask(start, middle);
            CountTask secondTask = new CountTask(middle + 1, end);
            firstTask.fork();
            secondTask.fork();
            int firstResult = firstTask.join();
            int secondResult = secondTask.join();
            sum = firstResult + secondResult;
        }
        return sum;
    }
}
