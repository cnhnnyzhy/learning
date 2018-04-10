package com.zhy.java.thread.book1.chapter03.eg3_8;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class ThreadMain {
    public static void main(String[] args){
        Count count = new Count();
        ThreadA task = new ThreadA(count);
        task.setName("线程A");
        task.start();

        ThreadB taskB = new ThreadB(count);
        taskB.setName("线程B");
        taskB.start();

    }
}
