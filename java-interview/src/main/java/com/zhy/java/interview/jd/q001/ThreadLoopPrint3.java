package com.zhy.java.interview.jd.q001;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：通过Java多线程方式实现循环顺序打印A、B，而且保证无论多少次循环，都不乱序？
     其实这个问题，背后考察的是一个生产者和消费者的问题。即：要保证当前一个线程的任务完成之后，再去执行另一个线程的任务。有多种解决方案：
     1）第一种：利用wait和notify方法和synchronized关键字联合完成。
     2）第二种：利用重入锁ReentrantLock和Condition
 */
public class ThreadLoopPrint3 {
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition aCondition = reentrantLock.newCondition();
    Condition bCondition = reentrantLock.newCondition();

    public static void main(String[] args){
        ThreadLoopPrint3 print = new ThreadLoopPrint3();
        A a = new A(print.reentrantLock, print.aCondition, print.bCondition);
        B b = new B(print.reentrantLock, print.aCondition, print.bCondition);

        a.start();
        b.start();
    }
}
