package com.zhy.java.algorithm.sort;

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
