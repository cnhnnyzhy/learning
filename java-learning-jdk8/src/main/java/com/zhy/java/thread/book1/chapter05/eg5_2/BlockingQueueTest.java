package com.zhy.java.thread.book1.chapter05.eg5_2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class BlockingQueueTest {
    public static void main(String[] args)throws Exception{
        final BlockingQueue<String> bq = new ArrayBlockingQueue<String>(16);
        for (int i=0; i<4; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String log = (String) bq.take();
                            parseLog(log);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        for(int i=0; i<16; i++){
            String log = (i + 1) + " --> ";
            bq.put(log);
        }
    }

    public static void parseLog(String log){
        System.out.println(log + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
