package com.zhy.java.io.nio;

import java.io.Serializable;

public class MyRequestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4533978558809793909L;
	
	private String name;
	private String value;
	private byte[] bytes;
	
	
	
	public MyRequestVO(String name, String value) {
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
		return "MyRequestVO [name=" + name + ", value=" + value + ", bytes=" + bytes.length + "]";
	}
}
