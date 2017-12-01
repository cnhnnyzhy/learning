package com.zhy.java.designpattern.strategy.demo01;

/**
 * 找乔国老帮忙，使孙权不能杀刘备
 * @author yang.zhang3
 * @create 2017/12/1
 */
public class BackDoor implements IStrategy {
    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }
}
