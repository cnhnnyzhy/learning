package com.zhy.java.thread.book1.chapter03.eg3_11;

/**
 * 第二种：线程安全，但是高并发性能不是很高的写法。
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2(){

    }
    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
