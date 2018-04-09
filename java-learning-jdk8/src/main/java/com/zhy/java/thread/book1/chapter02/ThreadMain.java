package com.zhy.java.thread.book1.chapter02;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ThreadMain {
    public static void main(String[] args){
        ThreadB threadB = new ThreadB();
        for (int i=0; i<5; i++){
            new Thread(threadB, String.format("线程_%s", i)).start();
        }
        Thread threadMain = Thread.currentThread();
        System.out.println("这是主线程；");
        System.out.println(String.format("返回当前线程的线程组中活动线程的数目：%s", Thread.activeCount()));
        System.out.println(String.format("主线程的名称：%s", threadMain.getName()));

        System.out.println(String.format("返回该线程的标识符：%s", threadMain.getId()));
        System.out.println(String.format("返回该线程的优先级：%s", threadMain.getPriority()));
        System.out.println(String.format("返回该线程的状态：%s", threadMain.getState()));
        System.out.println(String.format("返回该线程所属的线程组：%s", threadMain.getThreadGroup()));
        System.out.println(String.format("返回该线程是否为守护线程：%s", threadMain.isDaemon()));

        try {
            Thread.sleep(10000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
