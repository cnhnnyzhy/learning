package com.zhy.java.algorithm.sort;

/**
 * 插入排序算法
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] datas = {9, 2, 24, 90, 89, 55, 0};
        for (int i=1; i<datas.length; i++){
            if(datas[i] < datas[i-1]){
                int temp = datas[i];
                for (int j=i-1; j>=0; j--){
                    if(temp < datas[j]){
                        //交换位置
                        datas[j+1]=datas[j];
                        datas[j] = temp;
                    }
                }
            }
        }
        for (int a : datas) {
            System.out.print(a + " ");
        }
    }
}
