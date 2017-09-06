package com.zhy.java.jdk8;

import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class TestMain {

	@Test
	public void testI(){
		int i = 5;
		i = ++i + ++i;
		System.out.println(i);
		
		AtomicLong t = new AtomicLong();
		t.set(-1L);
		System.out.println(t.get());
	}
}
