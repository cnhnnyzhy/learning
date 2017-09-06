package com.zhy.java.basic;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class LongOrInt {

	public static void main(String[] args) {
		test2();
	}
	
	public static void test1(){
		final int a = 10;
		final long b = 10L;
		final long n[] = new long[1];
		long t1 = System.currentTimeMillis();
		IntStream.range(0, 1000).forEach(i->{
			LongStream.range(0, 10000000).forEach(j->{
				n[0] += a;
			});
		});
		long t2 = System.currentTimeMillis();
		System.out.println("计算结果：" + n[0]);
		System.out.println("计算时间：" + (t2 - t1));
		
		
		final long n1[] = new long[1];
		long t3 = System.currentTimeMillis();
		IntStream.range(0, 1000).forEach(i->{
			LongStream.range(0, 10000000).forEach(j->{
				n1[0] += b;
			});
		});
		long t4 = System.currentTimeMillis();
		System.out.println("计算结果：" + n1[0]);
		System.out.println("计算时间：" + (t4 - t3));
	}
	
	public static void test2(){
		final int a = 10;
		final long b = 10L;
		
		long n = 0;
		long t1 = System.currentTimeMillis();
		for(int i=0; i<1000; i++){
			for(int j=0; j<10000000; j++){
				n += a;
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("计算结果：" + n);
		System.out.println("计算时间：" + (t2 - t1));
		
		
		long n1 = 0;
		long t3 = System.currentTimeMillis();
		for(int i=0; i<1000; i++){
			for(int j=0; j<10000000;j++){
				n1+=b;
			}
		}
		long t4 = System.currentTimeMillis();
		System.out.println("计算结果：" + n1);
		System.out.println("计算时间：" + (t4 - t3));
	}

}
