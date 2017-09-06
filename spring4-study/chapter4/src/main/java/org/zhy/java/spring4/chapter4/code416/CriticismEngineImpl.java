package org.zhy.java.spring4.chapter4.code416;

import org.zhy.java.spring4.chapter4.code415.CriticismEngine;

public class CriticismEngineImpl implements CriticismEngine {

	@Override
	public String getCriticism() {
		int i = (int) (Math.random() * criticismPool.length);
		return criticismPool[i];
	}
	
	private String[] criticismPool;
	public void setCriticismPool(String[] criticismPool){
		this.criticismPool = criticismPool;
	}

}
