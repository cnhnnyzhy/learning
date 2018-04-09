package com.zhy.java.thread.book1.chapter02.eg2_3;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ThreadInterruptDemo implements Runnable {

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new ThreadInterruptDemo(), "InterruptDemo Thread");
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        System.out.println(String.format("线程是否中断：%s", thread.isInterrupted()));
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

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
        boolean stop = false;
        while (!stop){
            System.out.println("My Thread is running...");
            /*long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 1000){

            }
            if(Thread.currentThread().isInterrupted()){
                break;
            }*/

            try {
                Thread.sleep(3L);
            } catch (InterruptedException e) {
                break;
            }

        }
        System.out.println("My Thread exiting under request...");
    }
}
