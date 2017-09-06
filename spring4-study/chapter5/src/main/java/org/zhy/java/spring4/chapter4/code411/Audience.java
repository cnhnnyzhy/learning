package org.zhy.java.spring4.chapter4.code411;

import org.aspectj.lang.ProceedingJoinPoint;

public class Audience {
	public void watchPerformance(ProceedingJoinPoint jp){
		System.out.println("Silencing cell phones!");
		System.out.println("Taking seats!");
		try {
			jp.proceed();
			System.out.println("CLAP CLAP CLAP!!!");
		} catch (Throwable e) {
			System.out.println("Demanding a refund!");
			e.printStackTrace();
		}
	}
}
