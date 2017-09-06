package com.zhy.java.unsafe.allocateinstance;

public class MyClass {
	private static final long a;
	static{
		a = 10;
	}
	public long a(){
		return a;
	}
}
