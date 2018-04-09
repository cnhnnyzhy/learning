package com.zhy.java.thread.book1.chapter02;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ThreadB implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            Thread.sleep(50000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Thread curThread = Thread.currentThread();
        String curThreadName = curThread.getName();
        System.out.println(String.format("【%s】这是线程的名称：%s", curThreadName, curThreadName));
        System.out.println(String.format("【%s】返回当前线程的线程组中活动线程的数目：%s", curThreadName, Thread.activeCount()));
        System.out.println(String.format("【%s】返回该线程的标识符：%s", curThreadName, curThread.getId()));
        System.out.println(String.format("【%s】返回该线程的优先级：%s", curThreadName, curThread.getPriority()));
        System.out.println(String.format("【%s】返回该线程的状态：%s", curThreadName, curThread.getState()));
        System.out.println(String.format("【%s】返回该线程所属的线程组：%s", curThreadName, curThread.getThreadGroup()));
        System.out.println(String.format("【%s】测试线程是否处于活动状态：%s", curThreadName, curThread.isAlive()));
        System.out.println(String.format("【%s】测试线程是否为守护线程：%s", curThreadName, curThread.isDaemon()));
    }
}
