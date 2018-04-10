package com.zhy.java.thread.book1.chapter05.eg5_11;

import java.util.concurrent.Semaphore;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class SemaphoreDemo {
    public static void main(String[] args){
        final Semaphore semaphore = new Semaphore(3);
        for (int i=0; i<20; i++){
            final int no = i;
            Runnable thread = new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("用户%s连接上了：", no));
                    try {
                        Thread.sleep(300L);
                        semaphore.acquire();
                        System.out.println(String.format("用户%s开始访问后台程序", no));
                        Thread.sleep(1000L);
                        System.out.println(String.format("用户%s访问结束", no));
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(thread).start();
        }
        System.out.println("Main thread end!");
    }
}
