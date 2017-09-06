package org.zhy.java.spring4.chapter4.code401;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Audience {
	/**表演之前**/
	@Before("execution(* concert.Performance.perform(..))")
	public void silenceCellPhones(){
		System.out.println("Silencing cell phones!");
	}
	/**表演之前**/
	@Before("execution(* concert.Performance.perform(..))")
	public void takeSeats(){
		System.out.println("Taking seats!");
	}
	/**表演之后**/
	@AfterReturning("execution(* concert.Performance.perform(..))")
	public void applause(){
		System.out.println("CLAP CLAP CLAP!!!");
	}
	/**表演失败之后**/
	@AfterThrowing("execution(* concert.Performance.perform(..))")
	public void demandRefund(){
		System.out.println("Demanding a refund!");
	}
}
