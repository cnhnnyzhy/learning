package com.zhy.java.algorithm.find;

/**
 * 二维数组中的查找
 * 题目：的一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的
 * 一个二维数组和一个整数，判断数组中是否含有该整数。
 * @author yang.zhang3
 * @create 2018/4/27
 */
public class ArrayFindOf2D {
    public static void main(String[] args){
        int[][] array = {{1, 2, 4, 6, 14},{2, 4, 7, 8, 16,}, {8, 9, 10, 11, 17}, {9, 12, 13, 15, 18}};
        find(array, 18);
    }
    private static void find(int[][] array, int num){
        if(array == null || array.length <= 0){
            return;
        }
        int rows = array.length;
        int columns = rows;
        //选取右上角的数作为对比
        int r = rows - 1;
        int c = 0;
        while (r >= 0 && c < columns) {
            int[] columnArray = array[r];
            if(columnArray == null || columnArray.length <= 0){
                r--;
            }
            columns = columnArray.length;
            int temp = array[r][c];
            if (temp == num) {
                System.out.println(String.format("%s所在的位置为[%s,%s]", num, r, c));
                break;
            } else if (temp > num) {
                r--;
            } else {
                c++;
            }
        }
    }
}
