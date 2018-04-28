package com.zhy.java.algorithm.merge;

/**
 * 两个有序数组合并
 * 题目：有两个排序的数组A1和A2，内存在A1的末尾有足够多的空余空间容纳A2.
 * 请实现一个函数，把A2中的所有数字插入到A1中并且所有的数字是排序的。
 * 思路：从尾到头比较A1和A2中的数字，并把较大的数字复制到A1的合适位置。
 * @author yang.zhang3
 * @create 2018/4/28
 */
public class SortArrayMerge {
    public static void main(String[] args){
        int[] second = {1, 2, 3, 4, 5, 6};
        int[] first = {6, 8, 11, 29, 44, 55, 78, 101};
        int[] newArray = merge(first, second);
        if(newArray != null) {
            for (int num : newArray) {
                System.out.print(num + " ");
            }
        }
    }
    private static int[] merge(int[] first, int[] second){
        int lenFirst, lenSecond;
        if(first == null || (lenFirst = first.length) <= 0){
            return second;
        }
        if(second == null || (lenSecond = second.length) <= 0){
            return first;
        }
        int[] newArray = new int[lenFirst + lenSecond];
        if(first[0] >= second[lenSecond - 1]){
            int k = 0;
            for (int i=0; i<lenSecond; i++){
                newArray[k++] = second[i];
            }
            for(int i=0; i<lenFirst; i++){
                newArray[k++] = first[i];
            }
        }else if(second[0] >= first[lenFirst - 1]){
            int k = 0;
            for(int i=0; i<lenFirst; i++){
                newArray[k++] = first[i];
            }
            for (int i=0; i<lenSecond; i++){
                newArray[k++] = second[i];
            }
        }else{
            int i = lenFirst - 1;
            int j = lenSecond - 1;
            int k = lenFirst + lenSecond - 1;
            while (k >= 0){
                if(i < 0 && j < 0){
                    break;
                }else if(i < 0 && j >= 0){
                    newArray[k--] = second[j--];
                }else if(j < 0 && i >= 0){
                    newArray[k--] = first[i--];
                }else {
                    if (first[i] > second[j]) {
                        newArray[k--] = first[i--];
                    } else if (first[i] < second[j]) {
                        newArray[k--] = second[j--];
                    } else {
                        newArray[k--] = first[i--];
                        newArray[k--] = second[j--];
                    }
                }
            }
        }
        return newArray;
    }
}
