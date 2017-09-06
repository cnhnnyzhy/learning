package org.zhy.java.spring4.chapter4.code402;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
	/**定义一个切点**/
	@Pointcut("execution(* concert.Performance.perform(..))")
	public void performance(){}
	
	/**表演之前**/
	@Before("performance()")
	public void silenceCellPhones(){
		System.out.println("Silencing cell phones!");
	}
	/**表演之前**/
	@Before("performance()")
	public void takeSeats(){
		System.out.println("Taking seats!");
	}
	/**表演之后**/
	@AfterReturning("performance()")
	public void applause(){
		System.out.println("CLAP CLAP CLAP!!!");
	}
	/**表演失败之后**/
	@AfterThrowing("performance()")
	public void demandRefund(){
		System.out.println("Demanding a refund!");
	}
}
