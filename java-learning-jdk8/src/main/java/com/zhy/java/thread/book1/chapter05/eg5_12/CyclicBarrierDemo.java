package com.zhy.java.thread.book1.chapter05.eg5_12;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class CyclicBarrierDemo {
    public static void main(String[] args){
        CyclicBarrier barrier = new CyclicBarrier(3, new TotalTask());
        BillTask work1 = new BillTask("111", barrier);
        BillTask work2 = new BillTask("222", barrier);
        BillTask work3 = new BillTask("333", barrier);
        work1.start();
        work2.start();
        work3.start();
        System.out.println("Main thread end");
    }

    static class TotalTask extends Thread{
        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         * @see #Thread(ThreadGroup, Runnable, String)
         */
        @Override
        public void run() {
            System.out.println("所有子任务都执行完成了，就开始执行主任务了");
        }
    }

    static class BillTask extends Thread{
        private String billName;
        private CyclicBarrier barrier;
        public BillTask(String workName, CyclicBarrier barrier){
            this.billName = workName;
            this.barrier = barrier;
        }

        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         * @see #Thread(ThreadGroup, Runnable, String)
         */
        @Override
        public void run() {
            System.out.println(String.format("市区：%s 运算开始", billName));
            try {
                Thread.sleep(1000L);
                System.out.println(String.format("市区：%s 运算完成，等待中....", billName));
                barrier.await();
                System.out.println(String.format("全部结束，市区：%s 开始后面的工作", billName));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
