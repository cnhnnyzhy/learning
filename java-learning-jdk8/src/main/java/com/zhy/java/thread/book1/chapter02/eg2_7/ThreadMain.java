package com.zhy.java.thread.book1.chapter02.eg2_7;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/4/9
 */
public class ThreadMain {
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        /**
         * Returns the current thread's "initial value" for this
         * thread-local variable.  This method will be invoked the first
         * time a thread accesses the variable with the {@link #get}
         * method, unless the thread previously invoked the {@link #set}
         * method, in which case the {@code initialValue} method will not
         * be invoked for the thread.  Normally, this method is invoked at
         * most once per thread, but it may be invoked again in case of
         * subsequent invocations of {@link #remove} followed by {@link #get}.
         * <p>
         * <p>This implementation simply returns {@code null}; if the
         * programmer desires thread-local variables to have an initial
         * value other than {@code null}, {@code ThreadLocal} must be
         * subclassed, and this method overridden.  Typically, an
         * anonymous inner class will be used.
         *
         * @return the initial value for this thread-local
         */
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public ThreadLocal<Integer> getThreadLocal() {
        return seqNum;
    }

    public int getNextNum(){
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args){
        ThreadMain sn = new ThreadMain();
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);

        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread{
        private ThreadMain sn;
        private TestClient(ThreadMain sn){
            this.sn = sn;
        }
        @Override
        public void run(){
            for(int i=0; i<3; i++){
                System.out.println(String.format("thread[%s] --> sn[%s]", Thread.currentThread().getName(), sn.getNextNum()));
            }
            sn.getThreadLocal().remove();
        }
    }
}
