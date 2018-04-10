package com.zhy.java.thread.book1.chapter03.eg3_11;

/**
 * 第一种：线程不安全的，不正确的写法。
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class Singleton1 {
    private static Singleton1 instance;
    private Singleton1(){

    }
    public static Singleton1 getInstance(){
        if(instance == null){
            instance = new Singleton1();
        }
        return instance;
    }
}
