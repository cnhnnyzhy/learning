package com.zhy.java.interview.jd.q001;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class B extends Thread{
    ReentrantLock reentrantLock;
    Condition aCondition;
    Condition bCondition;
    public B(ReentrantLock reentrantLock, Condition aCondition, Condition bCondition){
        this.reentrantLock = reentrantLock;
        this.aCondition = aCondition;
        this.bCondition = bCondition;
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
    public void run(){
        while (true){
            reentrantLock.lock();
            try{
                System.out.println("B");
                aCondition.signal();
                bCondition.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            reentrantLock.unlock();
        }
    }
}
