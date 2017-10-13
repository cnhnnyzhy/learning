package com.zhy.java.interview.jd.q001;

public class B extends Thread{
    ThreadLoopPrint3 print;
    public B(ThreadLoopPrint3 print){
        this.print = print;
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
            print.reentrantLock.lock();
            try{
                System.out.println("B");
                print.aCondition.signal();
                print.bCondition.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            print.reentrantLock.unlock();
        }
    }
}
