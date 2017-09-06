package com.zhy.java.basic;

import java.io.Console;

public class TestSystemConsole {

	public static void main(String[] args) {
		Console c = System.console();
		System.out.println(c);
		//System.console().printf("fdsafdasfdsa{0}", "123");
	}

}
