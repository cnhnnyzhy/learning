package com.zhy.java.algorithm.tree.find;

/**
 * ${DESCRIPTION}
 *
 * @author Ocean
 * @create 2017/10/17
 */
public class ThreadStackTest {
    public static int count;
    public static void main(String[] args){
        methd();
    }

    public static void methd(){
        count++;
        System.out.println(count);
        methd();
    }
}
