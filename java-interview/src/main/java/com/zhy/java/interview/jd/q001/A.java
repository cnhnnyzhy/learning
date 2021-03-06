package com.zhy.java.interview.jd.q001;

public class A  extends  Thread{
    ThreadLoopPrint3 print;
    public A(ThreadLoopPrint3 print){
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
                System.out.print("A,");
                print.bCondition.signal();
                print.aCondition.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            print.reentrantLock.unlock();
        }
    }
}
