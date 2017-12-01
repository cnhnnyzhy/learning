package com.zhy.java.designpattern.strategy.demo01;

/**
 * 孙夫人断后，挡住追兵
 * @author yang.zhang3
 * @create 2017/12/1
 */
public class BlockEnemy implements IStrategy {
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
