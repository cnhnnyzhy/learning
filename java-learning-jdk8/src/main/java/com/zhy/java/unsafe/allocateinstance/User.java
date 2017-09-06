package com.zhy.java.unsafe.allocateinstance;

public class User implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3748697592383741341L;
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new User(this.name, this.age);
	}
}
