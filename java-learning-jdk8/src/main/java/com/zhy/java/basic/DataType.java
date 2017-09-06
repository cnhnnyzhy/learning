package com.zhy.java.basic;

public class DataType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 127;
		Integer f = 127;
		Long g = 3L;
		
		System.out.println(c == d);//true
		int h = e + f;
		System.out.println(e == f);//false
		System.out.println(c == (a + b));//false
		System.out.println(c.equals(a + b));//true
		System.out.println(g == (a + b));//false
		System.out.println(g.equals(a + b));//true
	}

}
