package ru.javabegin.training.springwebflow.objects;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 7730377931313245319L;

	
	private String name;
	private String password;
	
	
	
	public User() {
	}

	public User(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
