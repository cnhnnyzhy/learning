package com.zhy.java.designpattern.strategy.demo01;

/**
 * 求吴国太开个绿灯
 *
 * @author yang.zhang3
 * @create 2017/12/1
 */
public class GivenGreenLight implements IStrategy {
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯,放行！");
    }
}
