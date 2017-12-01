package com.zhy.java.designpattern.strategy.demo01;

/**
 * 首先定一个策略接口，这是诸葛亮老人家给赵云的三个锦囊妙计的接口
 * @author yang.zhang3
 * @create 2017/12/1
 */
public interface IStrategy {
    /**
     * 每个锦囊妙计都是一个可执行的算法
     */
    void operate();
}
