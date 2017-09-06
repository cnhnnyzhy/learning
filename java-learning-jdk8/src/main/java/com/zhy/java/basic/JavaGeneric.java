package com.zhy.java.basic;

import java.util.ArrayList;
import java.util.List;

public class JavaGeneric {
	public static String method(List<String> list){
		System.out.println("method(List<String> list)");
		return "";
	}
	public static int method(List<Integer> list){
		System.out.println("method(List<Integer> list)");
		return 1;
	}
	
	public static void main(String[] args) {
		method(new ArrayList<String>());
		method(new ArrayList<Integer>());
	}
}
