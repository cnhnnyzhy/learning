package com.zhy.java.thread.book1.chapter03.eg3_8;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/10
 */
public class ThreadB extends Thread{
    private Count count;
    public ThreadB(Count count){
        this.count = count;
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
     */
    @Override
    public void run() {
        count.lockMethod();
    }
}
