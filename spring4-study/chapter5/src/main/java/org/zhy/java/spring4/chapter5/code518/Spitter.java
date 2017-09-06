package org.zhy.java.spring4.chapter5.code518;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Spitter {
	private Long id;
	@NotNull
	@Size(min=5, max=16)
	private String userName;
	@NotNull
	@Size(min=5, max=25)
	private String password;
	@NotNull
	@Size(min=2, max=30)
	private String firstName;
	@NotNull
	@Size(min=2, max=30)
	private String lastName;
	
	
	public Spitter() {
	}
	
	
	
	public Spitter(Long id, String userName, String password, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}



	public Spitter(String userName, String password, String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
