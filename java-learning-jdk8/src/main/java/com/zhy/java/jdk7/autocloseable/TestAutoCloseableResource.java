package com.zhy.java.jdk7.autocloseable;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2017/12/8
 */
public class TestAutoCloseableResource {
    public static void main(String[] args) {
        //使用try-with-resource时会自动关闭
        try (MyAutoCloseableResource myResource = new MyAutoCloseableResource()) {
            myResource.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //这种情况不会自动关闭资源
        try {
            MyAutoCloseableResource myResource1 = new MyAutoCloseableResource();
            myResource1.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
