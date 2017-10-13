package com.zhy.java.interview.jd.q001;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：通过Java多线程方式实现循环顺序打印A、B，而且保证无论多少次循环，都不乱序？
     其实这个问题，背后考察的是一个生产者和消费者的问题。即：要保证当前一个线程的任务完成之后，再去执行另一个线程的任务。有多种解决方案：
     1）第一种：利用wait和notify方法和synchronized关键字联合完成。
     2）第二种：利用重入锁ReentrantLock和Condition
 */
public class ThreadLoopPrint2 {
    Object lock = new Object();

    public static void main(String[] args){
        ThreadLoopPrint2 print = new ThreadLoopPrint2();
        A a = print.new A();
        B b = print.new B();

        a.start();
        b.start();
        System.out.println("test");
    }

    class A extends Thread{
        @Override
        public void run(){
            while (true){
                synchronized (lock) {
                    System.out.print("A,");
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class B extends Thread{
        @Override
        public void run(){
            while (true){
                synchronized (lock) {
                    try {
                        System.out.println("B");
                        lock.notify();
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
