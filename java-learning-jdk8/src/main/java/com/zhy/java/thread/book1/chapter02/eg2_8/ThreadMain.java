package com.zhy.java.thread.book1.chapter02.eg2_8;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ThreadMain {
    public static void main(String[] args){
        ThreadB task = new ThreadB();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandlerThreadB());
        thread.start();
    }
}
