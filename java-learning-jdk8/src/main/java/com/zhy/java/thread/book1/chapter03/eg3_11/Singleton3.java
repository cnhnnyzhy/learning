package com.zhy.java.thread.book1.chapter03.eg3_11;

/**
 * 第三种：线程安全，性能又高的，这种写法最为常见。
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class Singleton3 {
    private static volatile Singleton3 instance;
    private static byte[] lock = new byte[0];
    private Singleton3(){

    }
    public static Singleton3 getInstance(){
        if(instance == null){
            synchronized (lock) {
                if(instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
