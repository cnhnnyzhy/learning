package com.zhy.java.thread.book1.chapter02.eg2_8;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ExceptionHandlerThreadB implements Thread.UncaughtExceptionHandler {
    /**
     * Method invoked when the given thread terminates due to the
     * given uncaught exception.
     * <p>Any exception thrown by this method will be ignored by the
     * Java Virtual Machine.
     *
     * @param t the thread
     * @param e the exception
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An exception has bean captured");
        System.out.println(String.format("Thread:%s", t.getId()));
        System.out.println(String.format("Exception:%s:%s", e.getClass().getName(), e.getMessage()));
        System.out.println("Stack Trace:");
        e.printStackTrace(System.out);
        System.out.println(String.format("Thread status:%s", t.getState()));
    }
}
