package org.zhy.java.spring4.chapter4.code405;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
	/**定义一个切点**/
	@Pointcut("execution(* concert.Performance.perform(..))")
	public void performance(){}
	
	@Around("performance()")
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
