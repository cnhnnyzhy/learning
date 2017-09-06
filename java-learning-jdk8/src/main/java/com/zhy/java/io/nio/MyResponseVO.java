package com.zhy.java.io.nio;

import java.io.Serializable;

public class MyResponseVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private byte[] bytes;
	
	
	
	public MyResponseVO(String name, String value) {
		this.name = name;
		this.value = value;
		this.bytes = new byte[1024];
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "MyResponseVO [name=" + name + ", value=" + value + ", bytes=" + bytes.length + "]";
	}
}
