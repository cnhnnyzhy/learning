package com.zhy.java.algorithm.order.merge;

import java.util.Arrays;

/**
 * 归并排序
 * 归并排序 (merge sort) 是一类与插入排序、交换排序、选择排序不同的另一种排序方法。归并的含义是将两个或两个以上的有序表合并成一个新的有序表。
 * 归并排序有多路归并排序、两路归并排序 , 可用于内排序，也可以用于外排序。
 *
 * 算法思路:
 * ①把 n 个记录看成 n 个长度为1的有序子表；
 * ②进行两两归并使记录关键字有序，得到 n/2 个长度为 2 的有序子表；
 * ③重复第②步直到所有记录归并成一个长度为 n 的有序表为止。
 * @author Ocean
 * @date 2017-09-15
 */
public class MergeSort {
    public static void merge(int[] a, int low, int mid, int hight){
        int[] temp = new int[hight - low + 1];
        int i = low;//左指针
        int j = mid + 1;//右指针
        int k = 0;
        //把较小的数先移到新数组中
        while(i <= mid && j <= hight){
            if(a[i] < a[j]){
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        //把左边剩余的数移入数组
        while(i <= mid){
            temp[k++] = a[i++];
        }
        //把右边剩余的数移入数组
        while(j <= hight){
            temp[k++] = a[j++];
        }
        //把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++){
            a[k2 + low] = temp[k2];
        }
    }

    public static void mergeSort(int[] a, int low, int hight){
        int mid = (low + hight) / 2;
        if(low < hight){
            //左边
            mergeSort(a, low, mid);
            //右边
            mergeSort(a, mid + 1, hight);
            //左右归并
            merge(a, low, mid, hight);
            System.out.println(Arrays.toString(a));
        }
    }

    public static void main(String[] args){
        int a[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        mergeSort(a, 0, a.length - 1);
        System.out.println("排序结果：" + Arrays.toString(a));
    }
}
