package com.zhy.java.algorithm.sort;

/**
 * 选择排序算法
 *
 * @author yang.zhang3
 * @create 2018/4/16
 */
public class SelectSort {
    public static void main(String[] args){
        int[] a = {1, 15, 8, 10, 2, 5};
        for(int i=0;i<a.length-1; i++){
            int indexOfNextSmallest = getIndexOfSmallest(a, i, a.length - 1);
            swap(a, i, indexOfNextSmallest);
        }
        for(int n : a){
            System.out.print(n + " ");
        }
    }

    private static int getIndexOfSmallest(int[] a, int first, int last) {
        int min = a[first];
        int indexOfMin = first;
        for (int i=first + 1; i<=last; i++){
            if(min > a[i]){
                min = a[i];
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }

    private static void swap(int[] a, int i, int index) {
        int temp = a[i];
        a[i] = a[index];
        a[index] = temp;
    }
}
