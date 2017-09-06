package com.zhy.java.jdk8.interfaces;

public interface MyInterface {
	/**
	 * 接口静态方法，属于类方法
	 */
	public static void staticMethod(){
		System.out.println("interface static method!");
	}
	/**
	 * 接口默认方法，属于实例方法
	 */
	public default void defaultMethod(){
		System.out.println("interface default method!");
	}
}
