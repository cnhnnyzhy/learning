package com.zhy.java.thread.book1.chapter02.eg2_5;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ThreadB extends Thread{
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
        for (int i = 0; i < 5; i++){
            System.out.println(String.format("线程B第%s次执行！", i));
        }
        try {
            Thread.sleep(7);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
