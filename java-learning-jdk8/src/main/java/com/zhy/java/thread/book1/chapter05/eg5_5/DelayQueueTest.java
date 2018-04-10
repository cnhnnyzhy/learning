package com.zhy.java.thread.book1.chapter05.eg5_5;

import java.util.concurrent.DelayQueue;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class DelayQueueTest {
    public static void main(String[] args){
        final DelayQueue<Student> bq = new DelayQueue<>();
        for (int i=0; i<5; i++){
            Student student = new Student("学生" + i, Math.round((Math.random() * 10 + i)));
            bq.put(student);
        }
        System.out.println(String.format("bq.peek() %s", bq.peek().getName()));
    }
}
