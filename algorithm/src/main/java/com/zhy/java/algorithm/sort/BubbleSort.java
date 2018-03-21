package com.zhy.java.algorithm.sort;

/**
 * 冒泡排序
 * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
 * @author Ocean
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] datas = {9, 2, 24, 90, 89, 55, 0};
        for(int i=0; i<datas.length; i++){
            System.out.print("第"+i+"次:");
            print(datas);
            for(int j=0; j<datas.length - i - 1; j++){
                System.out.print("####"+j+":");
                print(datas);
                if(datas[j] > datas[j+1]){
                    int temp = datas[j];
                    datas[j] = datas[j + 1];
                    datas[j+1]=temp;
                }
            }
        }
        System.out.println("//////////////////////////////");
        print(datas);
    }
    private static void print(int[] datas){
        for(int a : datas){
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
