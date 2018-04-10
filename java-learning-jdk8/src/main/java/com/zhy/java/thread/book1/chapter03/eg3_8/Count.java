package com.zhy.java.thread.book1.chapter03.eg3_8;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class Count {
    private byte[] lock1 = new byte[1];
    private byte[] lock2 = new byte[1];
    public int num = 0;
    public void add(){
        synchronized (lock1){
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                num += 1;
            }
            System.out.println(String.format("%s-%s", Thread.currentThread().getName(), num));
        }
    }

    public void lockMethod(){
        synchronized (lock2){
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1){
                num += 1;
            }
            System.out.println(String.format("%s-%s", Thread.currentThread().getName(), num));
        }
    }
}
