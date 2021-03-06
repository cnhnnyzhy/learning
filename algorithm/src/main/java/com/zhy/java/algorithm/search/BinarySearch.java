package com.zhy.java.algorithm.search;

/**
 * 二分查找算法
 * 从一个有顺序的数组中查找某个元素
 * 1
 */
public class BinarySearch {
    public static void main(String[] args) {
        int num = 99;
        int[] datas = {1, 8, 9, 12, 15, 18, 29, 99};
        System.out.println(num+"在数组中的位置为：" + binarySearch(datas, num));
    }

    /**
     * 二分查找（折半查找），版本1
     * @param datas
     * @param num
     * @return
     */
    public static int binarySearch(int[] datas, int num){
        int low, hight, mid;
        low = 0;
        hight = datas.length - 1;
        int count = 0;
        //此处使用的是while循环，也可能使用递归方法调用的方式实现
        while(low <= hight){
            count++;
            System.out.println("循环"+count+"次");
            mid = (low + hight) / 2;
            if(datas[low] == num){
                return low;
            }
            if(datas[hight] == num){
                return hight;
            }
            if(datas[mid] == num){
                return mid;
            }
            if(datas[mid] > num){
            }
            if(datas[mid] < num){
                low = mid + 1;
                hight--;
            }
        }
        return -1;
    }
}
