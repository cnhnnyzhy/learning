package com.zhy.java.basic;
/**
 * 数组读取缓存友好性提升性能
 * @author yang.zhang3
 *
 */
public class ArrayCache {

	public static void main(String[] args) {
		int n = 10000;
		int[][] a = new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				a[i][j] = i + j;
			}
		}
		//a[i][j] 和 a[i][j+1]内存是连续的，很大可能在同一个cache line上
		long t1 = System.currentTimeMillis();
		long sum = 0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				sum += a[i][j];
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println(sum + ":" + (t2 - t1));
		
		
		//a[i][j] 和 a[i+1][j+1]内存很有可能内存不连续，很小的机率在同一个cache line上
		long t3 = System.currentTimeMillis();
		long sum1 = 0;
		for(int j=0;j<n;j++){
			for(int i=0;i<n;i++){
				sum1 += a[i][j];
			}
		}
		long t4 = System.currentTimeMillis();
		System.out.println(sum1 + ":" + (t4 - t3));
	}

}
