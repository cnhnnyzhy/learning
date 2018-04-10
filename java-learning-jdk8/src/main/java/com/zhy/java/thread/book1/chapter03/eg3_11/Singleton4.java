package com.zhy.java.thread.book1.chapter03.eg3_11;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 第四种：线程安全，性能又高的，这种写法也最为常见。
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class Singleton4 {
    private static volatile Singleton4 instance;
    private static ReentrantLock lock = new ReentrantLock();
    private Singleton4(){

    }
    public static Singleton4 getInstance(){
        if(instance == null){
            lock.lock();
            try {
                if(instance == null) {
                    instance = new Singleton4();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
}
