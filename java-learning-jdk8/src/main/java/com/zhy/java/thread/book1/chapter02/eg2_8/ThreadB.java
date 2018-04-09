package com.zhy.java.thread.book1.chapter02.eg2_8;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ThreadB implements Runnable {
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
        int number0 = Integer.parseInt("TTT");
    }
}
