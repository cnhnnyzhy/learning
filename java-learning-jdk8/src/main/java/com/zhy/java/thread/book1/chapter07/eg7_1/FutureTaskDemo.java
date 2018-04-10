package com.zhy.java.thread.book1.chapter07.eg7_1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class FutureTaskDemo {
    public static void main(String[] args){
        SonTask task1 = new SonTask("Thread Son1");
        FutureTask<String> f1 = new FutureTask<String>(task1);
        new Thread(f1).start();
        try {
            System.out.println(f1.get());
            FutureTask<Integer> f2 = new FutureTask<Integer>(new MyRun(), 22);
            new Thread(f2).start();
            System.out.println(String.format("result_%s", f2.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class SonTask implements Callable<String>{
    private String name;
    SonTask(String name){
        this.name = name;
    }
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        Thread.sleep(1000L);
        System.out.println(String.format("%s任务计算完成", name));
        return "result_11";
    }
}


class MyRun implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("特定线程2完成");
    }
}