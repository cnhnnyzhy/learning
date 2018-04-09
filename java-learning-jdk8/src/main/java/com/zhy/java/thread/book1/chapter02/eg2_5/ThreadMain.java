package com.zhy.java.thread.book1.chapter02.eg2_5;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ThreadMain {
    public static void main(String[] args){
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        threadA.setDaemon(true);

        threadB.start();
        threadA.start();

        Thread threadMain = Thread.currentThread();
        System.out.println(String.format("线程A是不是守护线程：%s", threadA.isDaemon()));
        System.out.println(String.format("线程B是不是守护线程：%s", threadB.isDaemon()));
        System.out.println(String.format("线程main是不是守护线程：%s", threadMain.isDaemon()));
    }
}
