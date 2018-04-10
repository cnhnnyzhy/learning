package com.zhy.java.thread.book1.chapter03.eg3_10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class Count {
    private AtomicInteger num = new AtomicInteger(0);
    public void wite(){
        System.out.println(num.getAndAdd(1));
    }

    public int read(){
       return num.get();
    }

    public static void main(String[] args){
        int threadNum = 100;
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(threadNum);
        Count count = new Count();
        for(int i=0; i<threadNum; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.wite();
                    latch2.countDown();
                }
            }).start();
        }
        latch1.countDown();
        try {
            latch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最大值：" + count.read());
    }
}
