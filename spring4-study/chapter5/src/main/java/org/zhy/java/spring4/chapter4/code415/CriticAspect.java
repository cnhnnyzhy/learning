package org.zhy.java.spring4.chapter4.code415;

public aspect CriticAspect {
	public CriticAspect(){}
	
	pointcut performance() : execution(* perform(..));
	
	afterReturning() : performance(){
		System.out.println(criticismEngine.getCriticism());
	}
	
	private CriticismEngine criticismEngine;
	
	public void setCriticismEngine(CriticismEngine criticismEngine){
		this.criticismEngine = criticismEngine;
	}
}
